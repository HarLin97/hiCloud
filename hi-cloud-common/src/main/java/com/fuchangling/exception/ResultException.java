package com.fuchangling.exception;

import com.fuchangling.enums.ApiStatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author harlin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public ResultException(ApiStatusCode apiStatusCode) {
        this.code = apiStatusCode.getCode();
        this.message = apiStatusCode.getMessage();
    }


}
