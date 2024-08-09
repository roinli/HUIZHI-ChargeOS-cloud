package com.jingli.admin.controller;

import com.jingli.admin.commons.BaseController;
import com.jingli.admin.commons.Response;
import com.jingli.admin.constant.Constant;
import com.jingli.admin.model.LoginForm;
import com.jingli.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 运营端登录
 *
 * @author Zfenor
 * @version V1.0
 **/
@RestController
@Api(tags = {"A1 运营端 登录-登出"})
public class AuthController extends BaseController {


    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public Response login(@Valid @ApiParam(value = "登录数据", required = true) LoginForm form) {
        return userService.signIn(form, getSession(), Constant.LOGIN_TYPE_NOT_OPERATOR);
    }
}
