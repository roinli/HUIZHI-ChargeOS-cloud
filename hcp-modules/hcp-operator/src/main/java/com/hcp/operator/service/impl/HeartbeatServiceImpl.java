package com.hcp.operator.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.HeartbeatMapper;
import com.hcp.system.api.domain.Heartbeat;
import com.hcp.operator.service.IHeartbeatService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 充电桩心跳数据Service业务层处理
 *
 * @author hcp
 * @date 2024-08-06
 */
@Service
public class HeartbeatServiceImpl implements IHeartbeatService
{
    @Autowired
    private HeartbeatMapper heartbeatMapper;

    /**
     * 查询充电桩心跳数据
     *
     * @param pileId 充电桩心跳数据主键
     * @return 充电桩心跳数据
     */
    @Override
    public Heartbeat selectHeartbeatByPileId(String pileId)
    {
        return heartbeatMapper.selectById(pileId);
    }

    /**
     * 查询充电桩心跳数据列表-分页
     *
     * @param heartbeat 充电桩心跳数据
     * @return 充电桩心跳数据
     */
    @Override
    public IPage<Heartbeat> selectHeartbeatPage(Heartbeat heartbeat)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return heartbeatMapper.selectHeartbeatList(mpPage,heartbeat);
    }
    /**
     * 查询充电桩心跳数据列表
     *
     * @param heartbeat 充电桩心跳数据
     * @return 充电桩心跳数据
     */
    @Override
    public List<Heartbeat> selectHeartbeatList(Heartbeat heartbeat)
    {
        return heartbeatMapper.selectHeartbeatList(heartbeat);
    }

    /**
     * 新增充电桩心跳数据
     *
     * @param heartbeat 充电桩心跳数据
     * @return 结果
     */

    @Override
    public int insertHeartbeat(Heartbeat heartbeat)
    {
        return heartbeatMapper.insert(heartbeat);
    }

    /**
     * 修改充电桩心跳数据
     *
     * @param heartbeat 充电桩心跳数据
     * @return 结果
     */
    @Override
    public int updateHeartbeat(Heartbeat heartbeat)
    {
        Heartbeat selected = heartbeatMapper.getById(heartbeat.getPileId());
        if (null == selected){
            return heartbeatMapper.insertHeartBeat(heartbeat);
        }
        return heartbeatMapper.updateByPrimaryKey(heartbeat);
    }

    /**
     * 批量删除充电桩心跳数据
     *
     * @param pileIds 需要删除的充电桩心跳数据主键
     * @return 结果
     */
    @Override
    public int deleteHeartbeatByPileIds(String[] pileIds)
    {
        return heartbeatMapper.deleteHeartbeatByPileIds(pileIds);
    }

    /**
     * 删除充电桩心跳数据信息
     *
     * @param pileId 充电桩心跳数据主键
     * @return 结果
     */
    @Override
    public int deleteHeartbeatByPileId(String pileId)
    {
        return heartbeatMapper.deleteById(pileId);
    }
}
