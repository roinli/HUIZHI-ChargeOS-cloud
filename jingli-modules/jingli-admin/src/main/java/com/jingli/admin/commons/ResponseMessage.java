package com.jingli.admin.commons;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.io.Serializable;

/**
 * @author Zfenor
 * @Description 接口统一响应消息
 */

@Builder
@ApiModel(value = "ResponseMessage", description = "response通用信息")
public class ResponseMessage implements Serializable {

    /**
     * 接口返回信息成功success
     */
    public static final String SUCCESS_MSG = "success";
    /**
     * 接口返回信息成功failure
     */
    public static final String FAILURE_MSG = "failure";
    /**
     * 接口返回信息成功code码
     */
    public static final int SUCCESS_CODE = 0;
    /**
     * 接口返回信息失败code码
     */
    public static final int FAILURE_CODE = -1;
    private static final long serialVersionUID = -1779999523580810666L;
    /**
     * http状态码说明
     * http状态码:200-成功,500-服务器出错,404-链接无效
     */
    @ApiModelProperty(value = "http状态码:200-成功,500-服务器出错,404-链接无效")
    private int status = 200;
    /**
     * 接口返回状态码说明
     * 接口返回状态码:0-成功,负数-失败
     */
    @ApiModelProperty(value = "接口返回状态码说明 接口返回状态码:0-成功,负数-失败")
    private int code;
    /**
     * 接口返回信息
     * 接口返回信息:success-成功,failure-失败
     */
    @ApiModelProperty(value = "接口返回信息:success-成功,failure-失败")
    private String message;


    public ResponseMessage() {
    }

    public ResponseMessage(int status, int code) {
        this.status = status;
        this.code = code;
    }

    public ResponseMessage(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    protected static ResponseMessage ok() {
        return new ResponseMessage(200, SUCCESS_CODE, SUCCESS_MSG);
    }

    protected static ResponseMessage ok(int code, String message) {
        return new ResponseMessage(200, code, message);
    }


    protected static ResponseMessage ok(int status, int code, String message) {
        return new ResponseMessage(status, code, message);
    }

    protected static ResponseMessage error() {
        return new ResponseMessage(200, FAILURE_CODE, FAILURE_MSG);
    }

    protected static ResponseMessage error(String message) {
        return new ResponseMessage(200, FAILURE_CODE, message);
    }

    protected static ResponseMessage error(int code, String message) {
        return new ResponseMessage(200, code, message);
    }


    protected static ResponseMessage error(int status, int code, String message) {
        return new ResponseMessage(status, code, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseMessage that = (ResponseMessage) o;

        return Objects.equal(this.status, that.status) &&
                Objects.equal(this.code, that.code) &&
                Objects.equal(this.message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status, code, message);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("status", status)
                .add("code", code)
                .add("message", message)
                .toString();
    }
}

