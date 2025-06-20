package com.hcp.mp.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.mp.mapper.MiniappMapper;
import com.hcp.system.api.domain.Miniapp;
import com.hcp.mp.service.IMiniappService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 小程序Service业务层处理
 *
 * @author hcp
 * @date 2024-08-10
 */
@Service
public class MiniappServiceImpl implements IMiniappService
{
    @Autowired
    private MiniappMapper miniappMapper;

    /**
     * 查询小程序
     *
     * @param appId 小程序主键
     * @return 小程序
     */
    @Override
    public Miniapp selectMiniappByAppId(String appId)
    {
        return miniappMapper.selectById(appId);
    }

    /**
     * 查询小程序列表-分页
     *
     * @param miniapp 小程序
     * @return 小程序
     */
    @Override
    public IPage<Miniapp> selectMiniappPage(Miniapp miniapp)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return miniappMapper.selectMiniappList(mpPage,miniapp);
    }
    /**
     * 查询小程序列表
     *
     * @param miniapp 小程序
     * @return 小程序
     */
    @Override
    public List<Miniapp> selectMiniappList(Miniapp miniapp)
    {
        return miniappMapper.selectMiniappList(miniapp);
    }

    /**
     * 新增小程序
     *
     * @param miniapp 小程序
     * @return 结果
     */

    @Override
    public int insertMiniapp(Miniapp miniapp)
    {
        return miniappMapper.insert(miniapp);
    }

    /**
     * 修改小程序
     *
     * @param miniapp 小程序
     * @return 结果
     */
    @Override
    public int updateMiniapp(Miniapp miniapp)
    {
        return miniappMapper.updateById(miniapp);
    }

    /**
     * 批量删除小程序
     *
     * @param appIds 需要删除的小程序主键
     * @return 结果
     */
    @Override
    public int deleteMiniappByAppIds(String[] appIds)
    {
        return miniappMapper.deleteMiniappByAppIds(appIds);
    }

    /**
     * 删除小程序信息
     *
     * @param appId 小程序主键
     * @return 结果
     */
    @Override
    public int deleteMiniappByAppId(String appId)
    {
        return miniappMapper.deleteById(appId);
    }

    @Override
    public Miniapp getById(String id) {
        return miniappMapper.getById(id);
    }
}
