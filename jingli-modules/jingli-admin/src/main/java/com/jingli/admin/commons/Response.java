package com.jingli.admin.commons;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.jingli.admin.constant.UserErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zfenor
 * @Description 接口统一响应
 */

@ApiModel(value = "Response对象", description = "Response对象")
public class Response<T> {


    @ApiModelProperty(value = "接口操作返回值消息说明", name = "message", required = true)
    private ResponseMessage message;

    @ApiModelProperty(value = "接口操作返回值封装的数据", name = "data", required = true)
    private T data;

    public Response() {
        this(null, null);
    }

    public Response(T data) {
        this(null, data);
    }

    public Response(ResponseMessage message) {
        this.message = message;
    }

    public Response(ResponseMessage message, T data) {
        this.message = message;
        this.data = data;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Response<Void> ok() {
        return new Response(ResponseMessage.ok(), null);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})

    public static <T> Response<T> ok(T data) {
        return new Response(ResponseMessage.ok(), data);
    }

    public static Response<Void> ok(int code, String message) {
        return new Response(ResponseMessage.ok(code, message), null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Response<Void> ok(int status, int code, String message) {
        return new Response(ResponseMessage.ok(status, code, message), null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Response<T> ok(int status, int code, String message, T data) {
        return new Response(ResponseMessage.ok(status, code, message), data);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Response<Void> error() {
        return new Response(ResponseMessage.error(), null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Response<T> error(T data) {
        return new Response(ResponseMessage.error(), data);
    }

    public static Response<Void> error(int code, String message) {
        return new Response(ResponseMessage.error(code, message), null);
    }

    public static Response<Void> error(UserErrorEnum errorEnum) {
        return new Response(ResponseMessage.error(errorEnum.getCode(), errorEnum.getMessage()), null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Response<Void> error(int status, int code, String message) {
        return new Response(ResponseMessage.error(status, code, message), null);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Response<T> error(int status, int code, String message, T data) {
        return new Response(ResponseMessage.error(status, code, message), data);
    }

    public ResponseMessage getMessage() {
        return message;
    }

    public void setMessage(ResponseMessage message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Response<?> that = (Response<?>) o;

        return Objects.equal(this.message, that.message) &&
                Objects.equal(this.data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message, data);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("message", message)
                .add("data", data)
                .toString();
    }


}

