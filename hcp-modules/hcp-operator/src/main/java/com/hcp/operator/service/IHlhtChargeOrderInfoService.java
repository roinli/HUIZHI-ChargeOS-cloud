package com.hcp.operator.service;

import java.util.List;
import com.hcp.operator.domain.HlhtChargeOrderInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 充电站充电订单信息Service接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface IHlhtChargeOrderInfoService
{
    /**
     * 查询充电站充电订单信息
     *
     * @param orderNo 充电站充电订单信息主键
     * @return 充电站充电订单信息
     */
    HlhtChargeOrderInfo selectHlhtChargeOrderInfoByOrderNo(String orderNo);

    /**
     * 查询充电站充电订单信息列表-分页
     *
     * @param hlhtChargeOrderInfo 充电站充电订单信息
     * @return 充电站充电订单信息集合
     */
    IPage<HlhtChargeOrderInfo> selectHlhtChargeOrderInfoPage(HlhtChargeOrderInfo hlhtChargeOrderInfo);
    /**
     * 查询充电站充电订单信息列表
     *
     * @param hlhtChargeOrderInfo 充电站充电订单信息
     * @return 充电站充电订单信息集合
     */
    List<HlhtChargeOrderInfo> selectHlhtChargeOrderInfoList(HlhtChargeOrderInfo hlhtChargeOrderInfo);

    /**
     * 新增充电站充电订单信息
     *
     * @param hlhtChargeOrderInfo 充电站充电订单信息
     * @return 结果
     */
    int insertHlhtChargeOrderInfo(HlhtChargeOrderInfo hlhtChargeOrderInfo);

    /**
     * 修改充电站充电订单信息
     *
     * @param hlhtChargeOrderInfo 充电站充电订单信息
     * @return 结果
     */
    int updateHlhtChargeOrderInfo(HlhtChargeOrderInfo hlhtChargeOrderInfo);

    /**
     * 批量删除充电站充电订单信息
     *
     * @param orderNos 需要删除的充电站充电订单信息主键集合
     * @return 结果
     */
    int deleteHlhtChargeOrderInfoByOrderNos(String[] orderNos);

    /**
     * 删除充电站充电订单信息信息
     *
     * @param orderNo 充电站充电订单信息主键
     * @return 结果
     */
    int deleteHlhtChargeOrderInfoByOrderNo(String orderNo);
}
