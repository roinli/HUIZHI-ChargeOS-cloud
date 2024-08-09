
package com.jingli.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @ClassName ChargingOrderMapper
 * @Description:
 * @Author yangbin
 * @Version V1.0
 **/
@Repository
public interface OrderMapper extends BaseMapper<ChargingOrder> {

    List<OrderResult> getOrderList(@Param("params") Map<String, Object> params, Page<OrderResult> page);

    /**
     * 查询销售数据
     *
     * @param platForm 平台类型
     * @return 销售数据
     */
    Map<String, Object> getUsersAndSales(Map<String, Object> platForm);

    /**
     * 获取销售数据
     *
     * @param platForm 平台和代理商id
     * @return 销售数据
     */
    SalesData getSalesData(@Param("p") Map<String, Object> platForm);

    List<SalesDetail> getSalesDetail(Page<SalesDetail> page, @Param("p") Map<String, Object> platForm);

}

