package com.hcp.operator.service.impl;

import java.util.List;

import com.hcp.operator.mapper.OrderLogMapper;
import com.hcp.operator.service.IOrderLogService;
import com.hcp.system.api.domain.OrderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 订单操作日志Service业务层处理
 *
 * @author hcp
 * @date 2024-08-10
 */
@Service
public class OrderLogServiceImpl implements IOrderLogService
{
    @Autowired
    private OrderLogMapper orderLogMapper;

    /**
     * 查询订单操作日志
     *
     * @param id 订单操作日志主键
     * @return 订单操作日志
     */
    @Override
    public OrderLog selectOrderLogById(Long id)
    {
        return orderLogMapper.selectById(id);
    }

    /**
     * 查询订单操作日志列表-分页
     *
     * @param orderLog 订单操作日志
     * @return 订单操作日志
     */
    @Override
    public IPage<OrderLog> selectOrderLogPage(OrderLog orderLog)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return orderLogMapper.selectOrderLogList(mpPage,orderLog);
    }
    /**
     * 查询订单操作日志列表
     *
     * @param orderLog 订单操作日志
     * @return 订单操作日志
     */
    @Override
    public List<OrderLog> selectOrderLogList(OrderLog orderLog)
    {
        return orderLogMapper.selectOrderLogList(orderLog);
    }

    /**
     * 新增订单操作日志
     *
     * @param orderLog 订单操作日志
     * @return 结果
     */

    @Override
    public int insertOrderLog(OrderLog orderLog)
    {
        return orderLogMapper.insert(orderLog);
    }

    /**
     * 修改订单操作日志
     *
     * @param orderLog 订单操作日志
     * @return 结果
     */
    @Override
    public int updateOrderLog(OrderLog orderLog)
    {
        return orderLogMapper.updateById(orderLog);
    }

    /**
     * 批量删除订单操作日志
     *
     * @param ids 需要删除的订单操作日志主键
     * @return 结果
     */
    @Override
    public int deleteOrderLogByIds(Long[] ids)
    {
        return orderLogMapper.deleteOrderLogByIds(ids);
    }

    /**
     * 删除订单操作日志信息
     *
     * @param id 订单操作日志主键
     * @return 结果
     */
    @Override
    public int deleteOrderLogById(Long id)
    {
        return orderLogMapper.deleteById(id);
    }

    @Override
    public List<OrderLog> queryOrderLogByOrderNumber(String orderNumber) {
        return orderLogMapper.selectOrderLogListByOrderNumber(orderNumber);
    }
}
