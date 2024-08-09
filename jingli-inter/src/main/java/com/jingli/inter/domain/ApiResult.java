package com.jingli.inter.domain;


public class ApiResult<T> {
    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 结果状态，默认是false
     */
    private boolean flag ;

    /**
     * 结果
     */
    private T result;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    private ApiResult(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMessage();
        this.result = data;
    }

    /** 业务成功返回业务代码和描述信息 */
    public static ApiResult<Void> success() {
        return new ApiResult<Void>(ResultStatus.SUCCESS, null);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<T>(ResultStatus.SUCCESS, data);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> ApiResult<T> success(ResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return success(data);
        }
        return new ApiResult<T>(resultStatus, data);
    }

    /** 业务异常返回业务代码和描述信息 */
    public static <T> ApiResult<T> failure() {
        return new ApiResult<T>(ResultStatus.INTERNAL_SERVER_ERROR, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> ApiResult<T> failure(ResultStatus resultStatus) {
        return failure(resultStatus, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> ApiResult<T> failure(ResultStatus resultStatus, T data) {
        if (resultStatus == null) {
            return new ApiResult<T>(ResultStatus.INTERNAL_SERVER_ERROR, null);
        }
        return new ApiResult<T>(resultStatus, data);
    }
}
