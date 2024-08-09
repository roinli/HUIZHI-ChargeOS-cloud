
package com.jingli.admin.controller;

import com.alibaba.fastjson2.JSONObject;
import com.jingli.admin.commons.BaseController;
import com.jingli.admin.commons.Response;
import com.jingli.admin.constant.CacheConstant;
import com.jingli.admin.model.MenuPO;
import com.jingli.admin.model.User;
import com.jingli.admin.service.MenuService;
import com.jingli.common.core.utils.ServletUtils;
import com.jingli.common.core.utils.StringUtils;
import com.jingli.common.redis.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MenuController
 * @Description 菜单控制器
 * @author Zfenor
 * @Version V1.0
 **/
@Api(tags = "A9 运营者管理 --菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisService redisService;


    @ApiOperation("获取登录用户权限菜单(返回树形结构)")
    @GetMapping("/getUserMenu")
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    public Response<List<MenuPO>> getRoleMenu() {
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        if (StringUtils.isNotNull(httpServletRequest)) {
            Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
            System.out.println("token:{}" + "tk" + headers.get("token"));
            String userName = redisService.getCacheObject("tk" + headers.get("token"));
            User user = JSONObject.parseObject(redisService.getCacheObject(CacheConstant.User.PREFIX_NAME + userName), User.class);
            System.out.println("user:{}" + JSONObject.toJSONString(user));
            List<MenuPO> menuList = menuService.getAllPermissionMenu(user.getId());
            return Response.ok(menuList);
        } else {
            List<MenuPO> menuList = menuService.getAllPermissionMenu(getUserId());
            return Response.ok(menuList);
        }
    }
}
