package com.hcp.operator.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import com.hcp.system.api.domain.OrderLog;
import org.apache.ibatis.annotations.Param;
/**
 * 订单操作日志Mapper接口
 *
 * @author hcp
 * @date 2024-08-10
 */
public interface OrderLogMapper extends BaseMapperX<OrderLog>
{

    /**
     * 查询订单操作日志列表-分页
     *
     * @param orderLog 订单操作日志
     * @return 订单操作日志集合
     */
    IPage<OrderLog> selectOrderLogList(Page page,@Param("query") OrderLog orderLog);
    /**
     * 查询订单操作日志列表
     *
     * @param orderLog 订单操作日志
     * @return 订单操作日志集合
     */

    List<OrderLog> selectOrderLogList(@Param("query") OrderLog orderLog);
    @InterceptorIgnore(tenantLine = "1")
    List<OrderLog> selectOrderLogListByOrderNumber(@Param("orderNumber") String orderNumber);

    /**
     * 批量删除订单操作日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteOrderLogByIds(Long[] ids);
    @InterceptorIgnore(tenantLine = "1")
    int deleteByPrimaryKey(Long id);
    @InterceptorIgnore(tenantLine = "1")
    int insertOrderLog(OrderLog record);
    @InterceptorIgnore(tenantLine = "1")
    int insertSelective(OrderLog record);
    @InterceptorIgnore(tenantLine = "1")
    OrderLog selectByPrimaryKey(Long id);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKeySelective(OrderLog record);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKey(OrderLog record);
}
