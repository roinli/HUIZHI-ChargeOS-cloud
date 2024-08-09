
package com.jingli.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.commons.BaseController;
import com.jingli.admin.commons.Response;
import com.jingli.admin.model.Rule;
import com.jingli.admin.service.RuleService;
import com.jingli.admin.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.jingli.admin.constant.Constant.DEVICE_TYPE_TWO;

/**
 * @ClassName RuleController
 * @Description: 规则管理控制器
 * @Author yangbin
 **/
@Slf4j
@Validated
@RestController
@RequestMapping("/sysRule")
@Api(tags = {"A6 规则管理"})
public class SysRuleController extends BaseController {


    @Autowired
    private RuleService ruleService;

    @GetMapping("/getRuleList")
    @ApiOperation(value = "规则列表数据")
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    public Response getRuleList(@ApiParam("页数") @RequestParam("pageNo") int pageNo,
                                @ApiParam("每页显示数") @RequestParam("pageSize") int pageSize,
                                @ApiParam("平台类型") @RequestParam(value = "deviceType", required = false) Short deviceType,
                                @ApiParam("供应商ID") @RequestParam(value = "userId", required = false) Integer userId,
                                @ApiParam("规则类型，卡：10001 公众号：10002") @RequestParam(value = "ruleType", required = false) String ruleType,
                                @ApiParam("规则名称") @RequestParam(value = "ruleNm", required = false) String ruleNm) {
        // 根据代理商用户名查询规则列表
        Map<String, Object> params = new HashMap<>(8);
        params.put("userId", userId);
        if (StringUtils.isNotEmpty(ruleNm)) {
            params.put("ruleNm", ruleNm);
        }
        if (Objects.isNull(deviceType)) {
            deviceType = DEVICE_TYPE_TWO;
        }
        params.put("deviceType", deviceType);
        params.put("ruleType", ruleType);
        Page<Rule> page = new Page<>(pageNo, pageSize);
        page.addOrder(OrderItem.desc("createTime"));
        IPage<Rule> ruleList = ruleService.getRuleListByUserNm(params, page);
        return Response.ok(ruleList);
    }

}
