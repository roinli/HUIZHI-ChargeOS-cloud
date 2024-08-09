package com.jingli.admin.exception;

import lombok.Data;

/**
 * DESCRIPTION : 自定义异常
 * @author ducf
 */
@Data
public class ServiceException extends RuntimeException {


    private static final long serialVersionUID = 6855299010438900731L;
    private Integer code;

    public ServiceException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(Integer code, final String message) {
        super(message);
        this.code = code;
    }


}
