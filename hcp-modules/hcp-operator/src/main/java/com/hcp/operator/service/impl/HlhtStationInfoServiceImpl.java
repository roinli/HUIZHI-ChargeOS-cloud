package com.hcp.operator.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.HlhtStationInfoMapper;
import com.hcp.operator.domain.HlhtStationInfo;
import com.hcp.operator.service.IHlhtStationInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 站点信息Service业务层处理
 *
 * @author hcp
 * @date 2024-08-11
 */
@Service
public class HlhtStationInfoServiceImpl implements IHlhtStationInfoService
{
    @Autowired
    private HlhtStationInfoMapper hlhtStationInfoMapper;

    /**
     * 查询站点信息
     *
     * @param stationId 站点信息主键
     * @return 站点信息
     */
    @Override
    public HlhtStationInfo selectHlhtStationInfoByStationId(String stationId)
    {
        return hlhtStationInfoMapper.selectById(stationId);
    }

    /**
     * 查询站点信息列表-分页
     *
     * @param hlhtStationInfo 站点信息
     * @return 站点信息
     */
    @Override
    public IPage<HlhtStationInfo> selectHlhtStationInfoPage(HlhtStationInfo hlhtStationInfo)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return hlhtStationInfoMapper.selectHlhtStationInfoList(mpPage,hlhtStationInfo);
    }
    /**
     * 查询站点信息列表
     *
     * @param hlhtStationInfo 站点信息
     * @return 站点信息
     */
    @Override
    public List<HlhtStationInfo> selectHlhtStationInfoList(HlhtStationInfo hlhtStationInfo)
    {
        return hlhtStationInfoMapper.selectHlhtStationInfoList(hlhtStationInfo);
    }

    /**
     * 新增站点信息
     *
     * @param hlhtStationInfo 站点信息
     * @return 结果
     */

    @Override
    public int insertHlhtStationInfo(HlhtStationInfo hlhtStationInfo)
    {
        return hlhtStationInfoMapper.insert(hlhtStationInfo);
    }

    /**
     * 修改站点信息
     *
     * @param hlhtStationInfo 站点信息
     * @return 结果
     */
    @Override
    public int updateHlhtStationInfo(HlhtStationInfo hlhtStationInfo)
    {
        return hlhtStationInfoMapper.updateById(hlhtStationInfo);
    }

    /**
     * 批量删除站点信息
     *
     * @param stationIds 需要删除的站点信息主键
     * @return 结果
     */
    @Override
    public int deleteHlhtStationInfoByStationIds(String[] stationIds)
    {
        return hlhtStationInfoMapper.deleteHlhtStationInfoByStationIds(stationIds);
    }

    /**
     * 删除站点信息信息
     *
     * @param stationId 站点信息主键
     * @return 结果
     */
    @Override
    public int deleteHlhtStationInfoByStationId(String stationId)
    {
        return hlhtStationInfoMapper.deleteById(stationId);
    }
}
