package com.jingli.admin.exception;

import com.jingli.admin.commons.Response;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizi
 * @Description 404统一返回处理
 * @Version 1.0
 */
@RestController
public class ApiErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @GetMapping(value = ERROR_PATH)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Response error() {
        return Response.error(404, -4041, null);
    }

}
