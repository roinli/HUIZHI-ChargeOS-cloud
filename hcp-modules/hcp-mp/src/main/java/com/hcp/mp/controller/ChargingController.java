package com.hcp.mp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.domain.R;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.system.api.RemoteChargingService;
import com.hcp.system.api.domain.vo.ChargingPileVO;
import com.hcp.system.api.domain.vo.PlotDetailVo;
import com.hcp.system.api.domain.vo.PlotInfoReqVO;
import com.hcp.system.api.domain.vo.PlotVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/charging")
@Slf4j
@Api("充电桩列表相关接口")
public class ChargingController extends BaseController {
    @Resource
    private RemoteChargingService remoteChargingService;

    @PostMapping("/getPlotInfoPage")
    @ApiOperation("分页查询充电桩列表")
    public R<Page<PlotVO>> getPlotInfoPage(@RequestBody PlotInfoReqVO plotInfoReqVO) {
        R<Page<PlotVO>> infoPage = remoteChargingService.getPlotInfoPage(plotInfoReqVO);
        return infoPage;
    }

    @PostMapping("/getPlotInfo")
    @ApiOperation("查询充电桩列表")
    public R<List<PlotVO>> getPlotInfo(@RequestBody PlotInfoReqVO plotInfoReqVO) {
        R<List<PlotVO>> info = remoteChargingService.getPlotInfo(plotInfoReqVO);
        return info;
    }



    @GetMapping("/getChargingPileData")
    public R<ChargingPileVO> getChargingPileData(@RequestParam("key") String key) {
        R<ChargingPileVO> data = remoteChargingService.queryChargingPileData(key);
        return  data;
    }

    @GetMapping("/plotDetail")
    public R<PlotDetailVo> plotDetail(@RequestParam("plotId") String plotId,
                                      @RequestParam("deviceType") String deviceType) {
        R<PlotDetailVo> detailVoR = remoteChargingService.plotDetail(plotId, deviceType);
        return detailVoR;
    }



}
