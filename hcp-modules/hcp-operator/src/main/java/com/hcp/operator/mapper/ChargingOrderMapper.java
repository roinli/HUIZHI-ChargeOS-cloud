package com.hcp.operator.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.operator.constant.ChargeStatus;
import com.hcp.operator.domain.ManagerTotalDataVO;
import com.hcp.operator.domain.QueryChargePileVo;
import com.hcp.system.api.domain.ChargingOrder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 充电订单Mapper接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface ChargingOrderMapper extends BaseMapperX<ChargingOrder>
{

    /**
     * 查询充电订单列表-分页
     *
     * @param chargingOrder 充电订单
     * @return 充电订单集合
     */
    IPage<ChargingOrder> selectChargingOrderList(Page page,@Param("query") ChargingOrder chargingOrder);

    /**
     * 查询充电订单列表-分页
     * @param page
     * @param chargingOrder 充电订单
     * @return
     */
    @InterceptorIgnore(tenantLine = "1")
    Page<ChargingOrder> selectChargingOrderListPage(Page page,@Param("query") ChargingOrder chargingOrder);

    /**
     * 查询充电订单列表
     *
     * @param chargingOrder 充电订单
     * @return 充电订单集合
     */
    List<ChargingOrder> selectChargingOrderList(@Param("query") ChargingOrder chargingOrder);

    /**
     * 批量删除充电订单
     *
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteChargingOrderByOrderIds(String[] orderIds);

    ManagerTotalDataVO getManageTotalData( @Param("pileVo") QueryChargePileVo vo);

    @InterceptorIgnore(tenantLine = "1")
    ChargingOrder selectChargingOrderByOrderNumber( @Param("orderNumber") String orderNumber);

    //忽略租户信息查询订单信息
    @InterceptorIgnore(tenantLine = "1")
    ChargingOrder findChargingOrder(@Param("pileId") String pileId, @Param("portId") Long portId, @Param("chargingStatus")String chargingStatus);
    @InterceptorIgnore(tenantLine = "1")
    ChargingOrder getById(@Param("orderId") String orderId);


    @InterceptorIgnore(tenantLine = "1")
    int deleteByPrimaryKey(Long id);
    @InterceptorIgnore(tenantLine = "1")
    int insertOrder(ChargingOrder record);
    @InterceptorIgnore(tenantLine = "1")
    int insertSelective(ChargingOrder record);
    @InterceptorIgnore(tenantLine = "1")
    ChargingOrder selectByPrimaryKey(Long id);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKeySelective(ChargingOrder record);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKey(ChargingOrder record);
}
