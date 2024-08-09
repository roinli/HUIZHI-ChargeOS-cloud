package com.jingli.admin.exception;

import com.jingli.admin.commons.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.MissingFormatArgumentException;

/**
 * @author lizi
 * @Description 拦截 controller throw 的错误
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandlerAdvice {


    @ExceptionHandler(value = {ConstraintViolationException.class, MissingServletRequestParameterException.class,
            TypeMismatchException.class, IllegalArgumentException.class, IllegalStateException.class,
            MissingFormatArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response constraintViolationException(HttpServletRequest request, Exception ex) {
        log.error(request.getRequestURI() + "?" + request.getQueryString(), ex);
        return Response.error(500, -5001, ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response unknownException(HttpServletRequest request, Exception ex) {
        log.error(request.getRequestURI() + "?" + request.getQueryString(), ex);
        return Response.error(500, -5002, ex.getMessage());
    }


    /**
     * swagger 入参校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class,})
    public Response validHandle(Exception e) {
        String errMsg = "";
        if (e instanceof BindException) {
            errMsg = ((BindException) e).getFieldError().getDefaultMessage();
        }
        if (e instanceof MethodArgumentNotValidException) {
            errMsg = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        }
        return Response.error(errMsg);
    }

    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ServiceException.class})
    public Response serviceExceptionHandle(ServiceException e) {
        String errMsg = e.getMessage();
        Integer code = -1;
        if (null != e.getCode()) {
            code = e.getCode();
        }
        return Response.error(code, errMsg);
    }

}
