package tech.xxlab.sd.response;

import lombok.Data;
import tech.xxlab.sd.exceptions.ErrorDetail;

import java.util.List;

/**
 * @author houfachao
 */
@Data
public class ErrorResponse extends BaseResponse {

    private List<ErrorDetail> detail;
}