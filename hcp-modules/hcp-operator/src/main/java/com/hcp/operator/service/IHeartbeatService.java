package com.hcp.operator.service;

import java.util.List;
import com.hcp.system.api.domain.Heartbeat;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 充电桩心跳数据Service接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface IHeartbeatService
{
    /**
     * 查询充电桩心跳数据
     *
     * @param pileId 充电桩心跳数据主键
     * @return 充电桩心跳数据
     */
    Heartbeat selectHeartbeatByPileId(String pileId);

    /**
     * 查询充电桩心跳数据列表-分页
     *
     * @param heartbeat 充电桩心跳数据
     * @return 充电桩心跳数据集合
     */
    IPage<Heartbeat> selectHeartbeatPage(Heartbeat heartbeat);
    /**
     * 查询充电桩心跳数据列表
     *
     * @param heartbeat 充电桩心跳数据
     * @return 充电桩心跳数据集合
     */
    List<Heartbeat> selectHeartbeatList(Heartbeat heartbeat);

    /**
     * 新增充电桩心跳数据
     *
     * @param heartbeat 充电桩心跳数据
     * @return 结果
     */
    int insertHeartbeat(Heartbeat heartbeat);

    /**
     * 修改充电桩心跳数据
     *
     * @param heartbeat 充电桩心跳数据
     * @return 结果
     */
    int updateHeartbeat(Heartbeat heartbeat);

    /**
     * 批量删除充电桩心跳数据
     *
     * @param pileIds 需要删除的充电桩心跳数据主键集合
     * @return 结果
     */
    int deleteHeartbeatByPileIds(String[] pileIds);

    /**
     * 删除充电桩心跳数据信息
     *
     * @param pileId 充电桩心跳数据主键
     * @return 结果
     */
    int deleteHeartbeatByPileId(String pileId);
}
