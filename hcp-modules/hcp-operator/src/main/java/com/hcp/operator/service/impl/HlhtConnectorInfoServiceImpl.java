package com.hcp.operator.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.HlhtConnectorInfoMapper;
import com.hcp.operator.domain.HlhtConnectorInfo;
import com.hcp.operator.service.IHlhtConnectorInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 接口信息Service业务层处理
 *
 * @author hcp
 * @date 2024-08-11
 */
@Service
public class HlhtConnectorInfoServiceImpl implements IHlhtConnectorInfoService
{
    @Autowired
    private HlhtConnectorInfoMapper hlhtConnectorInfoMapper;

    /**
     * 查询接口信息
     *
     * @param connectorId 接口信息主键
     * @return 接口信息
     */
    @Override
    public HlhtConnectorInfo selectHlhtConnectorInfoByConnectorId(String connectorId)
    {
        return hlhtConnectorInfoMapper.selectById(connectorId);
    }

    /**
     * 查询接口信息列表-分页
     *
     * @param hlhtConnectorInfo 接口信息
     * @return 接口信息
     */
    @Override
    public IPage<HlhtConnectorInfo> selectHlhtConnectorInfoPage(HlhtConnectorInfo hlhtConnectorInfo)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return hlhtConnectorInfoMapper.selectHlhtConnectorInfoList(mpPage,hlhtConnectorInfo);
    }
    /**
     * 查询接口信息列表
     *
     * @param hlhtConnectorInfo 接口信息
     * @return 接口信息
     */
    @Override
    public List<HlhtConnectorInfo> selectHlhtConnectorInfoList(HlhtConnectorInfo hlhtConnectorInfo)
    {
        return hlhtConnectorInfoMapper.selectHlhtConnectorInfoList(hlhtConnectorInfo);
    }

    /**
     * 新增接口信息
     *
     * @param hlhtConnectorInfo 接口信息
     * @return 结果
     */

    @Override
    public int insertHlhtConnectorInfo(HlhtConnectorInfo hlhtConnectorInfo)
    {
        return hlhtConnectorInfoMapper.insert(hlhtConnectorInfo);
    }

    /**
     * 修改接口信息
     *
     * @param hlhtConnectorInfo 接口信息
     * @return 结果
     */
    @Override
    public int updateHlhtConnectorInfo(HlhtConnectorInfo hlhtConnectorInfo)
    {
        return hlhtConnectorInfoMapper.updateById(hlhtConnectorInfo);
    }

    /**
     * 批量删除接口信息
     *
     * @param connectorIds 需要删除的接口信息主键
     * @return 结果
     */
    @Override
    public int deleteHlhtConnectorInfoByConnectorIds(String[] connectorIds)
    {
        return hlhtConnectorInfoMapper.deleteHlhtConnectorInfoByConnectorIds(connectorIds);
    }

    /**
     * 删除接口信息信息
     *
     * @param connectorId 接口信息主键
     * @return 结果
     */
    @Override
    public int deleteHlhtConnectorInfoByConnectorId(String connectorId)
    {
        return hlhtConnectorInfoMapper.deleteById(connectorId);
    }
}
