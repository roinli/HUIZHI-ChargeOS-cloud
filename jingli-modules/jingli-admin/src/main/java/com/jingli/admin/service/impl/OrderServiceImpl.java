
package com.jingli.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.dataScope.DataScope;
import com.jingli.admin.mapper.OrderMapper;
import com.jingli.admin.model.OrderResult;
import com.jingli.admin.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ChargingOrderServiceImpl
 * @Description: 订单实现类
 * @Author yangbin
 * @Version V1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper chargingOrderMapper;

    /**
     * @描述 获取订单列表（分页）
     * @参数 [page, orderQueryForm 查询参数]
     * @返回值 com.baomidou.mybatisplus.core.metadata.IPage<java.util.Map < java.lang.String, java.lang.Object>>
     * @创建人 yangbin
     * @创建时间 2019/10/5
     */
    @Override
    @DataScope(alias = "d.userid")
    public IPage<OrderResult> getOrderList(Map<String, Object> params, Page<OrderResult> page) {
        List<OrderResult> orderList = chargingOrderMapper.getOrderList(params, page);
        return page.setRecords(orderList);
    }
}
