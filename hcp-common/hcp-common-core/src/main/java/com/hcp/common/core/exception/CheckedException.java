package com.hcp.common.core.exception;

/**
 * 检查异常
 *
 * @author vctgo
 */
public class CheckedException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public CheckedException(String message)
    {
        super(message);
    }

    public CheckedException(Throwable cause)
    {
        super(cause);
    }

    public CheckedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
