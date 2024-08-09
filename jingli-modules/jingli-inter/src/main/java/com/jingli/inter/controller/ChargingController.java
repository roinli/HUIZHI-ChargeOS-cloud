package com.jingli.inter.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jingli.inter.domain.ApiResult;
import com.jingli.inter.domain.ChargingPileVO;
import com.jingli.inter.domain.NewsDto;
import com.jingli.inter.domain.PlotVO;
import com.jingli.inter.entity.ChargingPort;
import com.jingli.inter.service.ChargingPileService;
import com.jingli.inter.service.ChargingPortService;
import com.jingli.inter.vo.req.PlotInfoReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/outer/charging")
@Slf4j
@Api("充电桩列表相关接口")
public class ChargingController {
    @Resource
    private ChargingPileService chargingPileService;
    @Resource
    private ChargingPortService chargingPortService;

    @Value("${pile.id.length}")
    private Integer pileIdLength;

    @GetMapping("/getPlotInfoPage")
    @ApiOperation("分页查询充电桩列表")
    public ApiResult<PageInfo<PlotVO>> getPlotInfoPage(PlotInfoReqVO plotInfoReqVO) {
        PageHelper.startPage(plotInfoReqVO.getPageNo(), plotInfoReqVO.getPageSize());
        List<PlotVO> plotInfo = chargingPileService.getPlotInfo(plotInfoReqVO);
        PageInfo pageInfo = new PageInfo(plotInfo);
        return ApiResult.success(pageInfo);
    }

    @GetMapping("/getChargingPileData")
    public ApiResult<ChargingPileVO> getChargingPileData(@RequestParam("key") String key) {
        if (key.length() > pileIdLength) {
            key = key.substring(0, pileIdLength);
        }
        ChargingPileVO chargingPileVo = chargingPileService.queryChargingPileData(key);
        List<ChargingPort> portData = chargingPortService.queryChargingPilePortData(key);
        chargingPileVo.setList(portData);
        return ApiResult.success(chargingPileVo);
    }

    @GetMapping("/queryConfigByKey")
    @ApiOperation("根据key查询配置信息")
    public ApiResult<String> queryConfigByKey(@RequestParam("configKey") String configKey) {
        return ApiResult.success(chargingPileService.queryConfigByKey(configKey));
    }

    @GetMapping("/queryNewsByNewsConfigKey")
    @ApiOperation("根据类型查看文章")
    public ApiResult<NewsDto> queryNewsByNewsConfigKey(@RequestParam("newsConfigKey") String newsConfigKey,
                                                       @RequestParam("type") String type
    ) {
        NewsDto newsDto = chargingPileService.queryNewsByNewsConfigKey(newsConfigKey, type);
        return ApiResult.success(newsDto);
    }

    @GetMapping("/queryNewsNameList")
    @ApiOperation("根据类型查看列表")
    public ApiResult<List<NewsDto>> queryNewsNameList(@RequestParam("type") String type) {
        List<NewsDto> list = chargingPileService.queryNewsNameList(type);
        return ApiResult.success(list);
    }


}
