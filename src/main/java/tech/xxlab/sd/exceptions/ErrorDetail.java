package tech.xxlab.sd.exceptions;

import lombok.Data;

import java.util.List;

/**
 * @author houfachao
 */
@Data
public class ErrorDetail {

    private List<String> loc;

    private String msg;

    private String type;
}
