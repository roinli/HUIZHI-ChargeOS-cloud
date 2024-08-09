package com.jingli.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.commons.BaseController;
import com.jingli.admin.commons.PageQueryParams;
import com.jingli.admin.commons.Response;
import com.jingli.admin.model.PlotResult;
import com.jingli.admin.service.PlotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.jingli.admin.constant.Constant.PLAT_FORM;

/**
 * @描述 站点管理--控制器
 * @创建人 yangbin
 */
@Validated
@Api(tags = "A14 站点管理")
@RequestMapping("/plot")
@RestController
public class PlotController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(PlotController.class);

    @Autowired
    private PlotService plotService;

    @ApiOperation("获取站点列表")
    @GetMapping("/getPlotList")
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    public Response<IPage<PlotResult>> getPlotList(PageQueryParams p,
                                                   @ApiParam("搜索关键字") @RequestParam(value = "keyWord", required = false) String keyWord) {
        logger.info("获取站点列表数据");
        Page<PlotResult> page = new Page<>(p.getPageNo(), p.getPageSize());
        Map<String, Object> params = new HashMap<>(4);
        params.put("keyWord", keyWord);
        params.put(PLAT_FORM, getPlatForm());
        // 如果是代理商就过滤代理商数据
        if (isOperator()) {
            params.put("userId", getUserId());
        }
        IPage<PlotResult> plotList = plotService.getPlotList(params, page);
        return Response.ok(plotList);
    }
}
