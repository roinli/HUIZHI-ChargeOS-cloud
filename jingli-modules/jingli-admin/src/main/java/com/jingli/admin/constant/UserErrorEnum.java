package com.jingli.admin.constant;

/**
 * @author lizi
 * @Description 用户错误码
 */
public enum UserErrorEnum {
    /**
     * 用户错误码
     */
    USER_NAME_EXIST(10000, "username already exist"),
    USER_NAME_NOT_EXIST(10001, "username not exist"),
    EMAIL_ERROR(10100, "邮箱格式错误"),
    PASSWORD_LENGTH_ERROR(10101, "密码长度不对"),
    PASSWORD_ERROR(10102, "密码错误"),
    NOT_SING_IN(-6, "用户未登录或身份异常"),
    SIGN_IN_INPUT_FAIL(-4, "账号或密码错误"),

    SIGN_IN_INPUT_NULL(-4, "登录账号不存在,请重新输入"),
    DAILI_CANNOT_LOGIN(-5, "代理商不能登录运营端！"),
    USERID_IS_NULL(-11, "用户userid为空");


    private Integer code;

    private String message;

    UserErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
