package tech.xxlab.sd.request;

import tech.xxlab.sd.HttpMethodType;
import tech.xxlab.sd.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @param <T> 一组请求中对应的 Response ,需要实现 BaseResponse
 * @author houfachao
 */
public abstract class BaseRequest<T extends BaseResponse> {

    private int expires = 5;

    protected HttpMethodType httpMethod;

    private String uri;

    /**
     * 超时时间
     */
    private Integer connectTimeout;
    private Integer connectionRequestTimeout;
    private Integer socketTimeout;

    /**
     * @param uri        请求的 URI
     * @param httpMethod http 请求方法
     */
    protected BaseRequest(String uri, HttpMethodType httpMethod) {
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    private void uri(String uri) {
        this.uri = uri;
    }

    public int expires() {
        return expires;
    }

    public void expires(int expires) {
        this.expires = expires;
    }

    public HttpMethodType httpMethod() {
        return httpMethod;
    }

    public String uri() {
        return uri;
    }

    public Integer connectTimeout() {
        return connectTimeout;
    }

    public void connectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer connectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void connectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public Integer socketTimeout() {
        return socketTimeout;
    }

    public void socketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    /**
     * @return 一组请求中对应的 Response ,需要实现 BaseResponse
     */
    @JsonIgnore
    public abstract Class<T> responseClass();
}
