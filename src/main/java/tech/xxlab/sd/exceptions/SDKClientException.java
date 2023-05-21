package tech.xxlab.sd.exceptions;

import lombok.Data;
import tech.xxlab.sd.response.ErrorResponse;

/**
 * @author houfachao
 */
@Data
public class SDKClientException extends Exception {

    private String requestId;
    private String message;
    private String code;
    private ErrorResponse errorResponse;

    public SDKClientException(String code, String message) {
        this.code = code;
        this.message = message;

    }

    public SDKClientException(String code, String message, ErrorResponse errorResponse) {
        super(errorResponse.toString());
        this.code = code;
        this.message = message;
    }

}
