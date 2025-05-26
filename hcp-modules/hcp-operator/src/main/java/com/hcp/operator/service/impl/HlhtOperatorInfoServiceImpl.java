package com.hcp.operator.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.HlhtOperatorInfoMapper;
import com.hcp.operator.domain.HlhtOperatorInfo;
import com.hcp.operator.service.IHlhtOperatorInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 运营商信息Service业务层处理
 *
 * @author hcp
 * @date 2024-08-11
 */
@Service
public class HlhtOperatorInfoServiceImpl implements IHlhtOperatorInfoService
{
    @Autowired
    private HlhtOperatorInfoMapper hlhtOperatorInfoMapper;

    /**
     * 查询运营商信息
     *
     * @param operatorId 运营商信息主键
     * @return 运营商信息
     */
    @Override
    public HlhtOperatorInfo selectHlhtOperatorInfoByOperatorId(String operatorId)
    {
        return hlhtOperatorInfoMapper.selectById(operatorId);
    }

    /**
     * 查询运营商信息列表-分页
     *
     * @param hlhtOperatorInfo 运营商信息
     * @return 运营商信息
     */
    @Override
    public IPage<HlhtOperatorInfo> selectHlhtOperatorInfoPage(HlhtOperatorInfo hlhtOperatorInfo)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return hlhtOperatorInfoMapper.selectHlhtOperatorInfoList(mpPage,hlhtOperatorInfo);
    }
    /**
     * 查询运营商信息列表
     *
     * @param hlhtOperatorInfo 运营商信息
     * @return 运营商信息
     */
    @Override
    public List<HlhtOperatorInfo> selectHlhtOperatorInfoList(HlhtOperatorInfo hlhtOperatorInfo)
    {
        return hlhtOperatorInfoMapper.selectHlhtOperatorInfoList(hlhtOperatorInfo);
    }

    /**
     * 新增运营商信息
     *
     * @param hlhtOperatorInfo 运营商信息
     * @return 结果
     */

    @Override
    public int insertHlhtOperatorInfo(HlhtOperatorInfo hlhtOperatorInfo)
    {
        return hlhtOperatorInfoMapper.insert(hlhtOperatorInfo);
    }

    /**
     * 修改运营商信息
     *
     * @param hlhtOperatorInfo 运营商信息
     * @return 结果
     */
    @Override
    public int updateHlhtOperatorInfo(HlhtOperatorInfo hlhtOperatorInfo)
    {
        return hlhtOperatorInfoMapper.updateById(hlhtOperatorInfo);
    }

    /**
     * 批量删除运营商信息
     *
     * @param operatorIds 需要删除的运营商信息主键
     * @return 结果
     */
    @Override
    public int deleteHlhtOperatorInfoByOperatorIds(String[] operatorIds)
    {
        return hlhtOperatorInfoMapper.deleteHlhtOperatorInfoByOperatorIds(operatorIds);
    }

    /**
     * 删除运营商信息信息
     *
     * @param operatorId 运营商信息主键
     * @return 结果
     */
    @Override
    public int deleteHlhtOperatorInfoByOperatorId(String operatorId)
    {
        return hlhtOperatorInfoMapper.deleteById(operatorId);
    }
}
