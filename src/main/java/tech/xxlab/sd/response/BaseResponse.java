package tech.xxlab.sd.response;

/**
 * @author houfachao
 */
public abstract class BaseResponse {
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
