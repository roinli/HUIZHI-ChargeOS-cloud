package com.hcp.operator.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.domain.R;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.operator.service.*;
import com.hcp.system.api.domain.Bo.FeeRangeTime;
import com.hcp.system.api.domain.ChargingOrder;
import com.hcp.system.api.domain.ChargingPile;
import com.hcp.system.api.domain.ChargingPort;
import com.hcp.system.api.domain.Heartbeat;
import com.hcp.system.api.domain.vo.ChargingPileVO;
import com.hcp.system.api.domain.vo.PlotDetailVo;
import com.hcp.system.api.domain.vo.PlotInfoReqVO;
import com.hcp.system.api.domain.vo.PlotVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/charge")
@Slf4j
public class ChargeController extends BaseController {
    @Autowired
    private IHeartbeatService heartbeatService;
    @Autowired
    private IChargingPileService chargingPileService;
    @Autowired
    private ICustomPriceService customPriceService;
    @Autowired
    private IChargingPortService chargingPortService;
    @Autowired
    private IChargingOrderService chargingOrderService;

    //处理设备心跳
    @GetMapping("/heartBeat")
    R<String> saveHeartBeat(@RequestParam("pileId") String pileId){
        assert pileId != null;
        ChargingPile chargingPile = chargingPileService.getById(pileId);
        if(chargingPile == null){
            return R.fail("桩不存在");
        }
        Heartbeat heartbeat = Heartbeat.builder()
                .pileId(pileId).chargestate("0").deviceId("").sourcemsg("")
                .build();
        heartbeat.setTenantId(chargingPile.getTenantId());
        heartbeatService.updateHeartbeat(heartbeat);
        return R.ok();
    }

    /**
     * 注册设备，查询设备是否在平台上
     *
     * @param pileId 桩Id
     * @return 结果
     */
    @GetMapping("/checkPile")
    R<ChargingPile> checkPile(@RequestParam("pileId") String pileId){
        return R.ok(chargingPileService.getById(pileId));
    }

    /**
     * 查询计费规则
     *
     * @param pileId 设备Id
     * @return 计费规则
     */
    @GetMapping("/feeInfo")
    R<List<FeeRangeTime>> getFeeInfo(@RequestParam("pileId") String pileId){
        assert pileId != null;
        return R.ok(customPriceService.getPriceByPileId(pileId));
    }

    /**
     * 插枪状态更新
     *
     * @param pileId 桩Id
     * @param port   接口号
     * @param gunInsert   1插枪0拔枪
     * @return 结果
     */
    @GetMapping("/gunInsert")
    R<String> gunInsert(@RequestParam("pileId") String pileId, @RequestParam("port") String port,
                        @RequestParam("gunInsert") String gunInsert){
        chargingPortService.updateGunStatus(pileId,port,gunInsert);
        return R.ok();
    }

    /**
     * 开始充电
     *
     * @param pileId 桩编号
     * @param port   接口号
     * @param amount 充电金额
     * @return 结果
     */
    @GetMapping("/startCharge")
    R<ChargingOrder> startCharge(@RequestParam("pileId") String pileId, @RequestParam("port") String port,
                                 @RequestParam("userId")Long userId,@RequestParam("amount") BigDecimal amount,
                                 @RequestParam("hour")Integer hour){
        ChargingOrder order = chargingOrderService.startChargeOrder(pileId,port,userId,amount,hour);
        return R.ok(order);
    }

    /**
     * 结束充电
     *
     * @param pileId 桩编号
     * @param port   接口号
     * @return 结果
     */
    @GetMapping("/stopCharge")
    R<String> stopCharge(@RequestParam("pileId") String pileId, @RequestParam("port") String port){
        chargingOrderService.stopChargingOrder(pileId,port);
        return R.ok();
    }

    /**
     * 订单信息
     *
     * @param orderId     订单编号
     * @param totalPower  总耗电量
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param electricFee 电费
     * @param serviceFee  服务费
     * @param totalAmount 总费用
     * @param stopReason  结束原因
     * @return 结果
     */
    @GetMapping("/orderInfo")
    R<String> orderInfo(@RequestParam("orderId") String orderId, @RequestParam("totalPower") Double totalPower,
                        @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
                        @RequestParam("electricFee") BigDecimal electricFee, @RequestParam("serviceFee") BigDecimal serviceFee,
                        @RequestParam("totalAmount") BigDecimal totalAmount, @RequestParam("stopReason") String stopReason){
        chargingOrderService.handleOrderInfo(orderId,totalPower,totalAmount,electricFee,serviceFee,startTime,endTime,stopReason);
        return R.ok();
    }

    /**
     * 启动结果
     *
     * @param pileId      桩编号
     * @param port        接口号
     * @param orderId     订单号
     * @param startResult 启动结果
     * @param failReason  失败原因
     * @return 结果
     */
    @GetMapping("/startResult")
    R<String> startResult(@RequestParam("pileId") String pileId, @RequestParam("port") String port,
                          @RequestParam("orderId") String orderId, @RequestParam("startResult") String startResult,
                          @RequestParam("failReason") String failReason){
        chargingOrderService.updateOrderStart(orderId,pileId,port,startResult,failReason);
        return R.ok();
    }

    /**
     * 停止充电结果
     *
     * @param pileId     桩编号
     * @param port       接口号
     * @param stopResult 停止结果
     * @param failReason 失败原因
     * @return 停止结果
     */
    @GetMapping("/stopResult")
    R<String> stopResult(@RequestParam("pileId") String pileId, @RequestParam("port") String port,
                         @RequestParam("stopResult") String stopResult, @RequestParam("failReason") String failReason){
        chargingOrderService.updateOrderStop(pileId,port,stopResult,failReason);
        return R.ok();
    }

    /**
     * 充电中心跳
     * @param pileId 桩编号
     * @param port 接口号
     * @param orderId 订单号
     * @param status 接口状态
     * @param voltage 电压
     * @param electric 电流
     * @param soc soc
     * @param chargePower 已充电量
     * @param chargePrice 已充金额
     * @return 结果
     */
    @GetMapping("/chargingHeartBeat")
    R<String> chargingHeartBeat(@RequestParam("pileId") String pileId, @RequestParam("port") String port,
                                @RequestParam("orderId") String orderId, @RequestParam("status") String status,
                                @RequestParam("voltage") Double voltage, @RequestParam("electric") Double electric,
                                @RequestParam("soc") String soc, @RequestParam("chargePower") Double chargePower,
                                @RequestParam("chargeFee") BigDecimal chargeFee, @RequestParam("serviceFee") BigDecimal serviceFee,
                                @RequestParam("chargePrice") BigDecimal chargePrice){
        chargingOrderService.handleChargingHeartBeat(orderId,voltage,electric,soc,chargePower,chargeFee,serviceFee,chargePrice);
        return R.ok();
    }
    @PostMapping("/getPlotInfo")
    @ApiOperation("查询充电桩列表")
    public R<List<PlotVO>> getPlotInfo(@RequestBody PlotInfoReqVO plotInfoReqVO) {

        List<PlotVO> plotInfo = chargingPileService.getPlotInfo(plotInfoReqVO);

        return R.ok(plotInfo);
    }
    @GetMapping("/queryChargingPileData")
    @ApiOperation("查询充电桩列表")
    public R<ChargingPileVO> queryChargingPileData(@RequestParam("pileId") String pileId) {
        ChargingPileVO chargingPileVo = chargingPileService.queryChargingPileData(pileId);
        if (ObjectUtil.isNotEmpty(chargingPileVo)){
            List<ChargingPort> portData = chargingPortService.selectPortByPileId(pileId);
            chargingPileVo.setList(portData);
        }

        return R.ok(chargingPileVo);
    }

    @GetMapping("/plotDetail")
    public R<PlotDetailVo> plotDetail(  @RequestParam("plotId") String plotId,
                                              @RequestParam("deviceType") String deviceType) {
        PlotDetailVo plotDetailVo = chargingPileService.plotDetail(plotId,deviceType);
        return R.ok(plotDetailVo);
    }

    @PostMapping("/getPlotInfoPage")
    @ApiOperation("分页查询充电桩列表")
    R<Page<PlotVO>> getPlotInfoPage(@RequestBody PlotInfoReqVO plotInfoReqVO) {

        Page<PlotVO> plotInfo = chargingPileService.getPlotInfoPage(plotInfoReqVO);

        return R.ok(plotInfo);
    }

}
