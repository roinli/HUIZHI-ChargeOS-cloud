package com.hcp.operator.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.HlhtPlatformInfoMapper;
import com.hcp.operator.domain.HlhtPlatformInfo;
import com.hcp.operator.service.IHlhtPlatformInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 平台信息Service业务层处理
 *
 * @author hcp
 * @date 2024-08-11
 */
@Service
public class HlhtPlatformInfoServiceImpl implements IHlhtPlatformInfoService
{
    @Autowired
    private HlhtPlatformInfoMapper hlhtPlatformInfoMapper;

    /**
     * 查询平台信息
     *
     * @param id 平台信息主键
     * @return 平台信息
     */
    @Override
    public HlhtPlatformInfo selectHlhtPlatformInfoById(Long id)
    {
        return hlhtPlatformInfoMapper.selectById(id);
    }

    /**
     * 查询平台信息列表-分页
     *
     * @param hlhtPlatformInfo 平台信息
     * @return 平台信息
     */
    @Override
    public IPage<HlhtPlatformInfo> selectHlhtPlatformInfoPage(HlhtPlatformInfo hlhtPlatformInfo)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return hlhtPlatformInfoMapper.selectHlhtPlatformInfoList(mpPage,hlhtPlatformInfo);
    }
    /**
     * 查询平台信息列表
     *
     * @param hlhtPlatformInfo 平台信息
     * @return 平台信息
     */
    @Override
    public List<HlhtPlatformInfo> selectHlhtPlatformInfoList(HlhtPlatformInfo hlhtPlatformInfo)
    {
        return hlhtPlatformInfoMapper.selectHlhtPlatformInfoList(hlhtPlatformInfo);
    }

    /**
     * 新增平台信息
     *
     * @param hlhtPlatformInfo 平台信息
     * @return 结果
     */

    @Override
    public int insertHlhtPlatformInfo(HlhtPlatformInfo hlhtPlatformInfo)
    {
        return hlhtPlatformInfoMapper.insert(hlhtPlatformInfo);
    }

    /**
     * 修改平台信息
     *
     * @param hlhtPlatformInfo 平台信息
     * @return 结果
     */
    @Override
    public int updateHlhtPlatformInfo(HlhtPlatformInfo hlhtPlatformInfo)
    {
        return hlhtPlatformInfoMapper.updateById(hlhtPlatformInfo);
    }

    /**
     * 批量删除平台信息
     *
     * @param ids 需要删除的平台信息主键
     * @return 结果
     */
    @Override
    public int deleteHlhtPlatformInfoByIds(Long[] ids)
    {
        return hlhtPlatformInfoMapper.deleteHlhtPlatformInfoByIds(ids);
    }

    /**
     * 删除平台信息信息
     *
     * @param id 平台信息主键
     * @return 结果
     */
    @Override
    public int deleteHlhtPlatformInfoById(Long id)
    {
        return hlhtPlatformInfoMapper.deleteById(id);
    }
}
