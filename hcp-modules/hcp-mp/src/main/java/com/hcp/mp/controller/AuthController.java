package com.hcp.mp.controller;

import com.hcp.common.core.domain.R;
import com.hcp.common.core.exception.base.BaseException;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.mp.service.IMiniappService;
import com.hcp.mp.service.MpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "AuthV1Controller", description = "用户认证")
@RestController
@RequestMapping(value = "/v1/auth/")
public class AuthController {
    @Autowired
    private MpService mpService;

    @Operation(summary = "小程序登录接口")
    @PostMapping("/login")
    public R xcxLogin(@RequestParam("appid") String appid, @RequestParam("code")String code)
    {
        try {
            if (StringUtils.isBlank(code)) {
                return R.fail("code为空");
            }
            //签名验证
            return mpService.loginByCode(appid, code);
        }
        catch (BaseException ex){
            log.error(ex.toString());
            return R.fail(ex.getMessage());
        }
        catch (Exception ex){
            log.error("小程序登录接口", ex);
            return R.fail("内部服务错误");
        }
    }
    @Operation(summary = "小程序绑定手机号")
    @GetMapping("/appletBindMobile")
    public R appletBindMobile(@RequestParam("openId") String openId,
                                       @RequestParam("code") String code,
                                       @RequestParam("appid") String appid
    ) {
        R r = mpService.appletBindMobile(openId, code, appid);
        return r;
    }
}
