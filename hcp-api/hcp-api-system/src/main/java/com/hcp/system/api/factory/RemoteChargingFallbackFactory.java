package com.hcp.system.api.factory;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.domain.R;
import com.hcp.system.api.domain.Bo.FeeRangeTime;
import com.hcp.system.api.RemoteChargingService;
import com.hcp.system.api.domain.ChargingOrder;
import com.hcp.system.api.domain.ChargingPile;
import com.hcp.system.api.domain.vo.ChargingPileVO;
import com.hcp.system.api.domain.vo.PlotDetailVo;
import com.hcp.system.api.domain.vo.PlotInfoReqVO;
import com.hcp.system.api.domain.vo.PlotVO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class RemoteChargingFallbackFactory implements FallbackFactory<RemoteChargingService> {
    @Override
    public RemoteChargingService create(Throwable cause) {
        return new RemoteChargingService() {

            @Override
            public R<String> saveHeartBeat(String pileId) {
                return R.fail("心跳信息处理失败" + cause.getMessage());
            }

            @Override
            public R<ChargingPile> checkPile(String pileId) {
                return R.fail("注册处理失败" + cause.getMessage());
            }

            @Override
            public R<List<FeeRangeTime>> getFeeInfo(String pileId) {
                return R.fail("计费模型处理失败" + cause.getMessage());
            }

            @Override
            public R<String> gunInsert(String pileId, String port, String gunInsert) {
                return R.fail("处理失败" + cause.getMessage());
            }

            @Override
            public R<ChargingOrder> startCharge(String pileId, String port, Long userId, BigDecimal amount, Integer hour) {
                return R.fail("停止充电处理失败" + cause.getMessage());
            }


            @Override
            public R<String> stopCharge(String pileId, String port) {
                return R.fail("停止结果处理失败" + cause.getMessage());
            }

            @Override
            public R<String> orderInfo(String orderId, Double totalPower, String startTime, String endTime, BigDecimal electricFee, BigDecimal serviceFee, BigDecimal totalAmount, String stopReason) {
                return R.fail("订单信息处理失败" + cause.getMessage());
            }

            @Override
            public R<String> startResult(String pileId, String port, String orderId, String startResult, String failReason) {
                return R.fail("充电结果处理失败" + cause.getMessage());
            }

            @Override
            public R<String> stopResult(String pileId, String port, String stopResult, String failReason) {
                return R.fail("充电结束处理失败" + cause.getMessage());
            }

            @Override
            public R<String> chargingHeartBeat(String pileId, String port, String orderId, String status, Double voltage, Double electric, String soc, Double chargePower,
                                               BigDecimal chargeFee, BigDecimal serviceFee, BigDecimal chargePrice) {
                return R.fail("充电中心跳信息处理失败" + cause.getMessage());
            }

            @Override
            public R<List<PlotVO>> getPlotInfo(PlotInfoReqVO plotInfoReqVO) {
                return R.fail("getPlotInfo失败" + cause.getMessage());
            }

            @Override
            public R<ChargingPileVO> queryChargingPileData(String pileId) {
                return R.fail("queryChargingPileData失败" + cause.getMessage());
            }

            @Override
            public R<PlotDetailVo> plotDetail(String plotId, String deviceType) {
                return R.fail("plotDetail失败" + cause.getMessage());
            }

            @Override
            public R<Page<PlotVO>> getPlotInfoPage(PlotInfoReqVO plotInfoReqVO) {
                return R.fail("getPlotInfoPage失败" + cause.getMessage());
            }
        };
    }
}
