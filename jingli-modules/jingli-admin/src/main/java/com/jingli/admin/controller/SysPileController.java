package com.jingli.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.commons.BaseController;
import com.jingli.admin.commons.Response;
import com.jingli.admin.model.SysPileResult;
import com.jingli.admin.service.PileService;
import com.jingli.admin.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 充电桩管理
 *
 * @author Zfenor
 * @version V1.0
 **/
@RestController
@RequestMapping("/chargingpile")
@Api(value = "设备管理 ", tags = {"A3 充电桩管理"})
public class SysPileController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(SysPileController.class);
    @Resource
    private PileService chargingPileService;

    @ApiOperation(value = "获取充电桩分页列表")
    @GetMapping("/getChargingPileList")
    @ApiImplicitParam(paramType = "header", name = "token", value = "身份认证Token")
    public Response<IPage<SysPileResult>> getChargingPileList(@ApiParam("搜索关键字") @RequestParam(value = "keyWord", required = false) String keyWord,
                                                              @ApiParam("分销商") @RequestParam(value = "userId", required = false) String userId,
                                                              @ApiParam("设备类型") @RequestParam(value = "deviceType", required = false) Short deviceType,
                                                              @ApiParam("站点") @RequestParam(value = "plot", required = false) String plot,
                                                              @ApiParam("页数") @RequestParam("pageNo") int pageNo,
                                                              @ApiParam("每页显示数") @RequestParam("pageSize") int pageSize) {
        logger.info("获取充电桩分页列表");
        Map<String, String> params = new HashMap<>();
        //设备编号
        params.put("keyWord", keyWord);
        //分销商id
        params.put("userId", userId);
        if (!StringUtils.isEmpty(plot)) {
            params.put("plot", plot);
        }
        // 未传参数 就是2轮车
        if (Objects.isNull(deviceType)) {
            params.put("deviceType", "4");
        } else {
            params.put("deviceType", deviceType.toString());
        }
        Page<SysPileResult> page = new Page<>(pageNo, pageSize);
        page.addOrder(OrderItem.desc("createTime"));
        IPage<SysPileResult> pileList = chargingPileService.getPage(params, page);
        return Response.ok(pileList);
    }
}
