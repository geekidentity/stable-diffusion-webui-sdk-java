package tech.xxlab.sd;

import tech.xxlab.sd.exceptions.SDKClientException;
import tech.xxlab.sd.request.BaseRequest;
import tech.xxlab.sd.response.BaseResponse;
import tech.xxlab.sd.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * <p>示例代码</p><br>
<pre>
        SmartlinkClientConfiguration configuration = new SmartlinkClientConfiguration();

        *******************************
        // 这些是必须设置的参数
        configuration.setAccessKeyId("");
        configuration.setAccessKeySecret("");
        configuration.setHost(new HttpHost(""))
        *******************************

        configuration.set...  设置其他参数

        SmartlinkClient smartLinkClient = new SmartlinkClient(configuration);

        SaveCdrRequest saveCdrRequest = new SaveCdrRequest();
        // 设置属性 saveCdrRequest.set...
        try {
            // 请求成功正常返回对应的 response
            SaveCdrResponse saveCdrResponse = smartLinkClient.getResponseModel(saveCdrRequest);
        } catch (ServerException e) {
            // 服务器错误，大概率是出 bug 了
            e.printStackTrace();
        } catch (ClientException e) {
            // 客户端错误，参数校验没通过？做了不该做的事？反正是你的事，再看看你写的代码
            e.printStackTrace();
        }
 </pre>
 详细文档：<a href="http://kb.tinetcloud.com/pages/viewpage.action?pageId=6293887">http://kb.tinetcloud.com/pages/viewpage.action?pageId=6293887</a>
 * @author houfachao
 */
public abstract class BaseClient {

    private HttpClient httpClient;

    private final HttpHost httpHost;

    private final BaseClientConfiguration configuration;

    private final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

    private HttpClientBuilder httpClientBuilder = null;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public BaseClient(BaseClientConfiguration configuration) {
        this.configuration = configuration;
        config(this.configuration);
        httpClient = getHttpClient();
        httpHost = new HttpHost(configuration.getHost());
    }

    public <T extends BaseResponse> HttpResponse execute(BaseRequest<T> request)
            throws SDKClientException {
        return execute(request, httpHost);
    }

    public <T extends BaseResponse> HttpResponse execute(BaseRequest<T> request, HttpHost host)
            throws SDKClientException {
        try {
            String url = host.getSchemeName()+"://" + host.getHostName() + ":" + host.getPort() + request.uri();
            StringEntity entity;
            switch (request.httpMethod().toString()) {
                case "GET":
                    HttpGet get = new HttpGet(URI.create(url));
                    setRequestConfig(get, request);
                    return getHttpClient().execute(get);
                case "POST":
                    HttpPost post = new HttpPost(URI.create(url));
                    entity = new StringEntity(objectMapper.writeValueAsString(request), ContentType.APPLICATION_JSON);
                    post.setEntity(entity);
                    setRequestConfig(post, request);
                    return getHttpClient().execute(post);
                case "DELETE":
                    HttpDelete delete = new HttpDelete(URI.create(url));
                    setRequestConfig(delete, request);
                    return getHttpClient().execute(delete);
                default:
                    BasicHttpEntityEnclosingRequest httpRequest = new BasicHttpEntityEnclosingRequest(
                            request.httpMethod().toString(), request.uri());
                    if (request.httpMethod().hasContent()) {
                        entity = new StringEntity(objectMapper.writeValueAsString(request), ContentType.APPLICATION_JSON);
                        httpRequest.setEntity(entity);
                    }
                    return httpClient.execute(httpHost, httpRequest);
            }

        } catch (ClientProtocolException e) {
            throw new SDKClientException("SDK", "SDK 协议错误");
        } catch (IOException e) {
            throw new SDKClientException("SDK", e.getMessage());
        }
    }

    /**
     * 保证httpClient单例
     *
     */
    private HttpClient getHttpClient() {
        if (httpClient == null) {
            //多线程下多个线程同时调用getHttpClient容易导致重复创建httpClient对象的问题,所以加上了同步锁
            synchronized (this) {
                if (httpClient == null) {
                    httpClient = httpClientBuilder
                            .setKeepAliveStrategy(new tech.xxlab.sd.SdkConnectionKeepAliveStrategy(this.configuration.getKeepAliveDurationMillis()))
                            .build();
                }
            }
        }
        return httpClient;
    }


    public <T extends BaseResponse> T getResponseModel(BaseRequest<T> request, String host)
            throws  SDKClientException {
        return getResponseModel(request, new HttpHost(host));
    }

    public <T extends BaseResponse> T getResponseModel(BaseRequest<T> request)
            throws  SDKClientException {
        return getResponseModel(request, this.httpHost);
    }

    public <T extends BaseResponse> T getResponseModel(BaseRequest<T> request, HttpHost host)
            throws SDKClientException {
        HttpResponse response = execute(request, host);
        if (isSuccess(response)) {
            return readResponse(response, request.responseClass());
        } else {
            int statusCode = response.getStatusLine().getStatusCode();
            String msg;
            switch (statusCode) {
                case HttpStatus.SC_BAD_REQUEST:
                    try {
                        msg = readResponseString(response);
                    } catch (IOException e) {
                        throw new SDKClientException("BadRequest", "缺少请求参数");
                    }
                    throw new SDKClientException("BadRequest", "缺少请求参数: " + msg);
                case HttpStatus.SC_UNAUTHORIZED:

                    try {
                        // HttpClient有清理CLOSE_WAIT状态的机制，只有在读body操作后才会触发HttpClient Manager回收连接，
                        // 否则会被认为该连接一直在处理请求。因此在处理异常请求部分的代码中增加 EntityUtils.consume(response.getEntity()) 方法读body操作
                        EntityUtils.consume(response.getEntity());
                    } catch (IOException e) {
                        throw new SDKClientException("Unauthorized", "请配置账号和密码" + e.getMessage());
                    }
                    throw new SDKClientException("Unauthorized", "请配置账号和密码");
                case HttpStatus.SC_SERVICE_UNAVAILABLE:
                    try {
                        // HttpClient有清理CLOSE_WAIT状态的机制，只有在读body操作后才会触发HttpClient Manager回收连接，
                        // 否则会被认为该连接一直在处理请求。因此在处理异常请求部分的代码中增加 EntityUtils.consume(response.getEntity()) 方法读body操作
                        EntityUtils.consume(response.getEntity());

                    } catch (IOException e) {
                        throw new SDKClientException("ServiceUnavailable", "服务不可用，请稍后再试。" + e.getMessage());
                    }
                    throw new SDKClientException("ServiceUnavailable", "服务不可用，请稍后再试。");
                default:
                    ErrorResponse errorResponse;
                    errorResponse = readResponse(response, ErrorResponse.class);
                    assert errorResponse != null;
                    throw new SDKClientException("", errorResponse.toString(), errorResponse);
            }
        }
    }

    private boolean isSuccess(HttpResponse response) {
        StatusLine statusLine = response.getStatusLine();
        return statusLine != null && statusLine.getStatusCode() < 400;
    }

    protected String readResponseString(HttpResponse response) throws IOException {
        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0 , length);
        }
        return result.toString();
    }

    private <T extends BaseResponse> T readResponse(HttpResponse response, Class<T> clazz) {
        try {
            return objectMapper.readValue(response.getEntity().getContent(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private void config(tech.xxlab.sd.BaseClientConfiguration configuration) {
        connectionManager.setMaxTotal(configuration.getMaxConnections());
        connectionManager.setDefaultMaxPerRoute(configuration.getMaxConnectionsPerRoute());
        httpClientBuilder = HttpClientBuilder.create().setConnectionManager(connectionManager);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(configuration.getSocketTimeout())
                .setConnectTimeout(configuration.getConnectTimeout())
                .setConnectionRequestTimeout(configuration.getConnectionRequestTimeout())
                .build();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
    }

    /**
     * 对http请求进行设置
     *
     * @param httpRequestBase http请求
     */
    private void setRequestConfig(HttpRequestBase httpRequestBase, BaseRequest<? extends BaseResponse> request) {
        Integer socketTimeout = request.socketTimeout();
        Integer connectionRequestTimeout = request.connectionRequestTimeout();
        Integer connectTimeout = request.connectTimeout();
        RequestConfig.Builder custom = RequestConfig.custom();
        if (socketTimeout!=null){
            custom.setSocketTimeout(socketTimeout);
        }
        if (connectionRequestTimeout!=null){
            custom.setConnectionRequestTimeout(connectionRequestTimeout);
        }
        if (connectTimeout!=null){
            custom.setConnectTimeout(connectTimeout);
        }
        httpRequestBase.setConfig(custom.build());
    }
}
