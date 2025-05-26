package com.hcp.system.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.constant.ServiceNameConstants;
import com.hcp.common.core.domain.R;
import com.hcp.system.api.domain.Bo.FeeRangeTime;
import com.hcp.system.api.domain.ChargingOrder;
import com.hcp.system.api.domain.ChargingPile;
import com.hcp.system.api.domain.vo.ChargingPileVO;
import com.hcp.system.api.domain.vo.PlotDetailVo;
import com.hcp.system.api.domain.vo.PlotInfoReqVO;
import com.hcp.system.api.domain.vo.PlotVO;
import com.hcp.system.api.factory.RemoteMpFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户服务
 *
 * @author vctgo
 */
@FeignClient(contextId = "remoteChargeService", value = ServiceNameConstants.OPERATOR_SERVICE, fallbackFactory = RemoteMpFallbackFactory.class)
public interface RemoteChargingService {
    /**
     * 更新设备心跳
     *
     * @param pileId 充电桩Id
     * @return 结果
     */
    @GetMapping("/charge/heartBeat")
    R<String> saveHeartBeat(@RequestParam("pileId") String pileId);

    /**
     * 注册设备，查询设备是否在平台上
     *
     * @param pileId 桩Id
     * @return 结果
     */
    @GetMapping("/charge/checkPile")
    R<ChargingPile> checkPile(@RequestParam("pileId") String pileId);

    /**
     * 查询计费规则
     *
     * @param pileId 设备Id
     * @return 计费规则
     */
    @GetMapping("/charge/feeInfo")
    R<List<FeeRangeTime>> getFeeInfo(@RequestParam("pileId") String pileId);

    /**
     * 插枪状态更新
     *
     * @param pileId 桩Id
     * @param port   接口号
     * @param gunInsert   1插枪0拔枪
     * @return 结果
     */
    @GetMapping("/charge/gunInsert")
    R<String> gunInsert(@RequestParam("pileId") String pileId, @RequestParam("port") String port,
                        @RequestParam("gunInsert") String gunInsert);

    /**
     * 开始充电
     *
     * @param pileId 桩编号
     * @param port   接口号
     * @param amount 充电金额
     * @param hour 充电时长
     * @return 结果
     */
    @GetMapping("/charge/startCharge")
    R<ChargingOrder> startCharge(@RequestParam("pileId") String pileId,
                                 @RequestParam("port") String port,
                                 @RequestParam("userId") Long userId,
                                 @RequestParam("amount") BigDecimal amount,
                                 @RequestParam("hour") Integer hour);

    /**
     * 结束充电
     *
     * @param pileId 桩编号
     * @param port   接口号
     * @return 结果
     */
    @GetMapping("/charge/stopCharge")
    R<String> stopCharge(@RequestParam("pileId") String pileId, @RequestParam("port") String port);

    /**
     * 订单信息
     *
     * @param orderId     订单编号
     * @param totalPower  总耗电量
     * @param startTime   开始时间 (yyyy-MM-dd HH:mm:ss)
     * @param endTime     结束时间 (yyyy-MM-dd HH:mm:ss)
     * @param electricFee 电费
     * @param serviceFee  服务费
     * @param totalAmount 总费用
     * @param stopReason  结束时间
     * @return 结果
     */
    @GetMapping("/charge/orderInfo")
    R<String> orderInfo(@RequestParam("orderId") String orderId, @RequestParam("totalPower") Double totalPower,
                        @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
                        @RequestParam("electricFee") BigDecimal electricFee, @RequestParam("serviceFee") BigDecimal serviceFee,
                        @RequestParam("totalAmount") BigDecimal totalAmount, @RequestParam("stopReason") String stopReason);

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
    @GetMapping("/charge/startResult")
    R<String> startResult(@RequestParam("pileId") String pileId, @RequestParam("port") String port,
                          @RequestParam("orderId") String orderId, @RequestParam("startResult") String startResult,
                          @RequestParam("failReason") String failReason);

    /**
     * 停止充电结果
     *
     * @param pileId     桩编号
     * @param port       接口号
     * @param stopResult 停止结果
     * @param failReason 失败原因
     * @return 停止结果
     */
    @GetMapping("/charge/stopResult")
    R<String> stopResult(@RequestParam("pileId") String pileId, @RequestParam("port") String port,
                         @RequestParam("stopResult") String stopResult, @RequestParam("failReason") String failReason);

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
    @GetMapping("/charge/chargingHeartBeat")
    R<String> chargingHeartBeat(@RequestParam("pileId") String pileId, @RequestParam("port") String port,
                                @RequestParam("orderId") String orderId, @RequestParam("status") String status,
                                @RequestParam("voltage") Double voltage, @RequestParam("electric") Double electric,
                                @RequestParam("soc") String soc, @RequestParam("chargePower") Double chargePower,
                                @RequestParam("chargeFee") BigDecimal chargeFee, @RequestParam("serviceFee") BigDecimal serviceFee,
                                @RequestParam("chargePrice") BigDecimal chargePrice);
    @PostMapping("/charge/getPlotInfo")
    R<List<PlotVO>> getPlotInfo(@RequestBody PlotInfoReqVO plotInfoReqVO);

    @GetMapping("/charge/queryChargingPileData")
    R<ChargingPileVO> queryChargingPileData(@RequestParam("pileId") String pileId);

    @GetMapping("/charge/plotDetail")
    R<PlotDetailVo> plotDetail(@RequestParam("plotId") String plotId, @RequestParam("deviceType") String deviceType);

    @PostMapping("/charge/getPlotInfoPage")
    R<Page<PlotVO>> getPlotInfoPage(@RequestBody PlotInfoReqVO plotInfoReqVO);


}
