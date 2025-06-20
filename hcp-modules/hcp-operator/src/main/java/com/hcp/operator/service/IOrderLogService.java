package com.hcp.operator.service;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hcp.system.api.domain.OrderLog;

/**
 * 订单操作日志Service接口
 *
 * @author hcp
 * @date 2024-08-10
 */
public interface IOrderLogService
{
    /**
     * 查询订单操作日志
     *
     * @param id 订单操作日志主键
     * @return 订单操作日志
     */
    OrderLog selectOrderLogById(Long id);

    /**
     * 查询订单操作日志列表-分页
     *
     * @param orderLog 订单操作日志
     * @return 订单操作日志集合
     */
    IPage<OrderLog> selectOrderLogPage(OrderLog orderLog);
    /**
     * 查询订单操作日志列表
     *
     * @param orderLog 订单操作日志
     * @return 订单操作日志集合
     */
    List<OrderLog> selectOrderLogList(OrderLog orderLog);

    /**
     * 新增订单操作日志
     *
     * @param orderLog 订单操作日志
     * @return 结果
     */
    int insertOrderLog(OrderLog orderLog);

    /**
     * 修改订单操作日志
     *
     * @param orderLog 订单操作日志
     * @return 结果
     */
    int updateOrderLog(OrderLog orderLog);

    /**
     * 批量删除订单操作日志
     *
     * @param ids 需要删除的订单操作日志主键集合
     * @return 结果
     */
    int deleteOrderLogByIds(Long[] ids);

    /**
     * 删除订单操作日志信息
     *
     * @param id 订单操作日志主键
     * @return 结果
     */
    int deleteOrderLogById(Long id);

    List<OrderLog> queryOrderLogByOrderNumber(String orderNumber);
}
