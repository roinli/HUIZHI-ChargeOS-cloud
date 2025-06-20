package com.hcp.operator.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import com.hcp.system.api.domain.Heartbeat;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 充电桩心跳数据Mapper接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface HeartbeatMapper extends BaseMapperX<Heartbeat>
{

    /**
     * 查询充电桩心跳数据列表-分页
     *
     * @param heartbeat 充电桩心跳数据
     * @return 充电桩心跳数据集合
     */
    IPage<Heartbeat> selectHeartbeatList(Page page,@Param("query") Heartbeat heartbeat);
    /**
     * 查询充电桩心跳数据列表
     *
     * @param heartbeat 充电桩心跳数据
     * @return 充电桩心跳数据集合
     */
    List<Heartbeat> selectHeartbeatList(@Param("query") Heartbeat heartbeat);

    /**
     * 批量删除充电桩心跳数据
     *
     * @param pileIds 需要删除的数据主键集合
     * @return 结果
     */

    int deleteHeartbeatByPileIds(String[] pileIds);

    @InterceptorIgnore(tenantLine = "1")
    Heartbeat getById(String pileId);
    @InterceptorIgnore(tenantLine = "1")
    int deleteByPrimaryKey(String id);
    @InterceptorIgnore(tenantLine = "1")
    int insertHeartBeat(Heartbeat record);
    @InterceptorIgnore(tenantLine = "1")
    int insertSelective(Heartbeat record);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKeySelective(Heartbeat record);
    @InterceptorIgnore(tenantLine = "1")
    int updateByPrimaryKey(Heartbeat record);
}
