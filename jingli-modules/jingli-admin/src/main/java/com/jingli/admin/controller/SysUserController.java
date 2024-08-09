
package com.jingli.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.commons.BaseController;
import com.jingli.admin.commons.PageQueryParams;
import com.jingli.admin.commons.Response;
import com.jingli.admin.model.UserResult;
import com.jingli.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description 控制器
 * @Author yangbin
 **/
@Api(tags = "A9 运营者管理 --运营人员管理", value = "用户管理")
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = {"/getUserList"})
    @ApiOperation(value = "运营者列表数据")
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    public Response<IPage<UserResult>> getUserList(@ApiParam("搜索关键字") @RequestParam(value = "keyWord", required = false) String keyWord,
                                                   PageQueryParams p) {
        Page<UserResult> page = new Page<>(p.getPageNo(), p.getPageSize());
        Map<String, Object> params = new HashMap<>();
        /*搜索关键字*/
        params.put("keyWord", keyWord);
        IPage<UserResult> userResultIPage = userService.getUserList(params, page);
        return Response.ok(userResultIPage);
    }
}
