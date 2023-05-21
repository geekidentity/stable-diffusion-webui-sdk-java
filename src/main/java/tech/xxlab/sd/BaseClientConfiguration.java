package tech.xxlab.sd;

import org.apache.http.HttpHost;

/**
 * @author houfachao
 */
public abstract class BaseClientConfiguration {

    /**
     * keep alive 时间，默认为1 分钟
     */
    public static final long KEEP_ALIVE_DURATION_MILLIS = 60 * 1000L;

    private HttpHost host;

    private String accessKeyId = null;

    private String accessKeySecret = null;

    /**
     * connection pool
     */
    private int maxConnections = 100;
    private int maxConnectionsPerRoute = 100;

    /**
     * timeout
     */
    private int connectionRequestTimeout = 50;
    private int connectTimeout = 200;
    private int socketTimeout = 200;
    private long keepAliveDurationMillis = KEEP_ALIVE_DURATION_MILLIS;

    public BaseClientConfiguration() {
    }

    public BaseClientConfiguration(String accessKeyId, String accessKeySecret) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getMaxConnectionsPerRoute() {
        return maxConnectionsPerRoute;
    }

    public void setMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public long getKeepAliveDurationMillis() {
        return keepAliveDurationMillis;
    }

    public void setKeepAliveDurationMillis(long keepAliveDurationMillis) {
        this.keepAliveDurationMillis = keepAliveDurationMillis;
    }

    public HttpHost getHost() {
        return host;
    }

    public void setHost(String address){
        this.host = HttpHost.create(address);
    }

    public void setHost(HttpHost host) {
        this.host = host;
    }

    public void setHost(String host, int port) {
        this.host = new HttpHost(host, port);
    }
}
