package com.jingli.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.commons.BaseController;
import com.jingli.admin.commons.PageQueryParams;
import com.jingli.admin.commons.Response;
import com.jingli.admin.model.PilesDetail;
import com.jingli.admin.model.SalesData;
import com.jingli.admin.model.SalesDetail;
import com.jingli.admin.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static com.jingli.admin.constant.Constant.PLAT_FORM;

/**
 * @描述 统计--控制器
 * @创建人 yangbin
 */
@Api(tags = "A2 统计管理")
@Controller
@RequestMapping("/Statistics")
public class StatisticsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(StatisticsController.class);
    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation(value = "首页数据", notes = "usreCount:新增用户数 ,sumSales:销售总额")
    @GetMapping("getUsersAndSales")
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    @ResponseBody
    public Response getUsersAndSales() {
        Map<String, Object> params = new HashMap<>(16);
        Short platForm = (Short) getPlatForm();
        params.put(PLAT_FORM, platForm);
        if (isOperator()) {
            params.put("userId", getUserId());
        }
        Map<String, Object> data = statisticsService.getUsersAndSales(params);
        return Response.ok(data);
    }

    @ApiOperation("销售统计")
    @GetMapping("getSalesData")
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    @ResponseBody
    public Response<SalesData> getSalesData() {
        Map<String, Object> params = new HashMap<>(4);
        params.put(PLAT_FORM, getPlatForm());
        if (isOperator()) {
            params.put("userId", getUserId());
        }
        SalesData salesData = statisticsService.getSalesData(params);
        return Response.ok(salesData);
    }

    @ApiOperation("设备统计")
    @GetMapping("getPilesInfo")
    @ResponseBody
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    public Response<IPage<PilesDetail>> getPilesInfo(PageQueryParams p) {
        Page<PilesDetail> page = new Page<>(p.getPageNo(), p.getPageSize());
        Map<String, Object> params = new HashMap<>(4);
        params.put(PLAT_FORM, getPlatForm());
        if (isOperator()) {
            params.put("userId", getUserId());
        }
        IPage<PilesDetail> pages = statisticsService.getPilesDetail(page, params);
        return Response.ok(pages);
    }

    @ApiOperation("销售详情")
    @GetMapping("getSalesDetail")
    @ResponseBody
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    public Response<IPage<SalesDetail>> getSalesDetail(PageQueryParams p) {
        Page<SalesDetail> page = new Page<>(p.getPageNo(), p.getPageSize());
        Map<String, Object> paramMap = new HashMap<>();
        Short platForm = (Short) getPlatForm();
        paramMap.put(PLAT_FORM, platForm);
        if (isOperator()) {
            paramMap.put("userId", getUserId());
        }
        IPage<SalesDetail> salesDetails = statisticsService.getSalesDetail(page, paramMap);
        return Response.ok(salesDetails);
    }
}
