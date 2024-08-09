
package com.jingli.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.model.OrderResult;
import java.util.Map;


/**
 * @ClassName ChargingOrder
 * @Description:
 * @Author yangbin
 * @Version V1.0
 **/
public interface OrderService {

    /**
     * 订单列表
     *
     * @param params 参数
     * @param page   分页
     * @return 分页结果
     */
    IPage<OrderResult> getOrderList(Map<String, Object> params, Page<OrderResult> page);
}