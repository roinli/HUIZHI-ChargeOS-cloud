package com.hcp.system.api.factory;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.domain.R;
import com.hcp.system.api.RemoteChargeOrderService;
import com.hcp.system.api.domain.ChargingOrder;
import com.hcp.system.api.domain.OrderLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户服务降级处理
 *
 * @author vctgo
 */
@Component
@Slf4j
public class RemoteChargeOrderFallbackFactory implements FallbackFactory<RemoteChargeOrderService>
{

    @Override
    public RemoteChargeOrderService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteChargeOrderService()
        {
            @Override
            public R<ChargingOrder> getOrderByOrderNumber(String orderNumber) {
                return R.fail("获取订单信息失败:" + throwable.getMessage());
            }

            @Override
            public R<List<OrderLog>> queryOrderLogByOrderNumber( String orderNumber) {
                return R.fail("获取订单日志信息失败:" + throwable.getMessage());
            }

            @Override
            public R<Page<ChargingOrder>> queryOrderList(ChargingOrder chargingOrder) {
                return R.fail("获取订单列表失败:" + throwable.getMessage());
            }


        };

    }
}
