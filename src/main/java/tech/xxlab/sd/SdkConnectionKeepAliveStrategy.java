package tech.xxlab.sd;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/**
 * SDK keep alive 时间策略，默认为5 分钟
 * @author houfachao
 */
public class SdkConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {

    /**
     * 默认为 5分钟
     */
    private long keepAliveDurationMillis = tech.xxlab.sd.BaseClientConfiguration.KEEP_ALIVE_DURATION_MILLIS;

    public SdkConnectionKeepAliveStrategy(long keepAliveDurationMillis) {
        this.keepAliveDurationMillis = keepAliveDurationMillis;
    }

    @Override
    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
        return keepAliveDurationMillis;
    }
}
