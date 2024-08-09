package com.jingli.admin.controller;

import com.jingli.admin.commons.BaseController;
import com.jingli.admin.commons.Response;
import com.jingli.admin.service.ISysConfigService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数配置 信息操作处理
 * @author Zfenor
 */
@RestController
@RequestMapping("/sysConfig")
@Slf4j
@AllArgsConstructor
public class SysConfigController extends BaseController {
    private final ISysConfigService configService;

    @ApiOperation("根据参数键名查询参数值")
    @GetMapping(value = "/getInfoByKey")
    public Response getConfigKey(@RequestParam(value = "configKey") String configKey) {
        return Response.ok(configService.selectConfigByKey(configKey));
    }
}
