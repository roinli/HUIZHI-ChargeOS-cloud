package com.hcp.common.core.exception.user;

import com.hcp.common.core.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author vctgo
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
