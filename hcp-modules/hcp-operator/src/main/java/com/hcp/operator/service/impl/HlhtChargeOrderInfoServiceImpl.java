package com.hcp.operator.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.HlhtChargeOrderInfoMapper;
import com.hcp.operator.domain.HlhtChargeOrderInfo;
import com.hcp.operator.service.IHlhtChargeOrderInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 充电站充电订单信息Service业务层处理
 *
 * @author hcp
 * @date 2024-08-11
 */
@Service
public class HlhtChargeOrderInfoServiceImpl implements IHlhtChargeOrderInfoService
{
    @Autowired
    private HlhtChargeOrderInfoMapper hlhtChargeOrderInfoMapper;

    /**
     * 查询充电站充电订单信息
     *
     * @param orderNo 充电站充电订单信息主键
     * @return 充电站充电订单信息
     */
    @Override
    public HlhtChargeOrderInfo selectHlhtChargeOrderInfoByOrderNo(String orderNo)
    {
        return hlhtChargeOrderInfoMapper.selectById(orderNo);
    }

    /**
     * 查询充电站充电订单信息列表-分页
     *
     * @param hlhtChargeOrderInfo 充电站充电订单信息
     * @return 充电站充电订单信息
     */
    @Override
    public IPage<HlhtChargeOrderInfo> selectHlhtChargeOrderInfoPage(HlhtChargeOrderInfo hlhtChargeOrderInfo)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return hlhtChargeOrderInfoMapper.selectHlhtChargeOrderInfoList(mpPage,hlhtChargeOrderInfo);
    }
    /**
     * 查询充电站充电订单信息列表
     *
     * @param hlhtChargeOrderInfo 充电站充电订单信息
     * @return 充电站充电订单信息
     */
    @Override
    public List<HlhtChargeOrderInfo> selectHlhtChargeOrderInfoList(HlhtChargeOrderInfo hlhtChargeOrderInfo)
    {
        return hlhtChargeOrderInfoMapper.selectHlhtChargeOrderInfoList(hlhtChargeOrderInfo);
    }

    /**
     * 新增充电站充电订单信息
     *
     * @param hlhtChargeOrderInfo 充电站充电订单信息
     * @return 结果
     */

    @Override
    public int insertHlhtChargeOrderInfo(HlhtChargeOrderInfo hlhtChargeOrderInfo)
    {
        return hlhtChargeOrderInfoMapper.insert(hlhtChargeOrderInfo);
    }

    /**
     * 修改充电站充电订单信息
     *
     * @param hlhtChargeOrderInfo 充电站充电订单信息
     * @return 结果
     */
    @Override
    public int updateHlhtChargeOrderInfo(HlhtChargeOrderInfo hlhtChargeOrderInfo)
    {
        return hlhtChargeOrderInfoMapper.updateById(hlhtChargeOrderInfo);
    }

    /**
     * 批量删除充电站充电订单信息
     *
     * @param orderNos 需要删除的充电站充电订单信息主键
     * @return 结果
     */
    @Override
    public int deleteHlhtChargeOrderInfoByOrderNos(String[] orderNos)
    {
        return hlhtChargeOrderInfoMapper.deleteHlhtChargeOrderInfoByOrderNos(orderNos);
    }

    /**
     * 删除充电站充电订单信息信息
     *
     * @param orderNo 充电站充电订单信息主键
     * @return 结果
     */
    @Override
    public int deleteHlhtChargeOrderInfoByOrderNo(String orderNo)
    {
        return hlhtChargeOrderInfoMapper.deleteById(orderNo);
    }
}
