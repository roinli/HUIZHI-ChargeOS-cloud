package com.hcp.job.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.job.mapper.XxlJobRegistryMapper;
import com.hcp.job.domain.XxlJobRegistry;
import com.hcp.job.service.IXxlJobRegistryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 注册Service业务层处理
 *
 * @author vctgo
 * @date 2023-01-03
 */
@Service
public class XxlJobRegistryServiceImpl implements IXxlJobRegistryService
{
    @Autowired
    private XxlJobRegistryMapper xxlJobRegistryMapper;

    /**
     * 查询注册
     *
     * @param id 注册主键
     * @return 注册
     */
    @Override
    public XxlJobRegistry selectXxlJobRegistryById(Long id)
    {
        return xxlJobRegistryMapper.selectById(id);
    }

    /**
     * 查询注册列表-分页
     *
     * @param xxlJobRegistry 注册
     * @return 注册
     */
    @Override
    public IPage<XxlJobRegistry> selectXxlJobRegistryPage(XxlJobRegistry xxlJobRegistry)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return xxlJobRegistryMapper.selectXxlJobRegistryList(mpPage,xxlJobRegistry);
    }
    /**
     * 查询注册列表
     *
     * @param xxlJobRegistry 注册
     * @return 注册
     */
    @Override
    public List<XxlJobRegistry> selectXxlJobRegistryList(XxlJobRegistry xxlJobRegistry)
    {
        return xxlJobRegistryMapper.selectXxlJobRegistryList(xxlJobRegistry);
    }

    /**
     * 新增注册
     *
     * @param xxlJobRegistry 注册
     * @return 结果
     */

    @Override
    public int insertXxlJobRegistry(XxlJobRegistry xxlJobRegistry)
    {
        return xxlJobRegistryMapper.insert(xxlJobRegistry);
    }

    /**
     * 修改注册
     *
     * @param xxlJobRegistry 注册
     * @return 结果
     */
    @Override
    public int updateXxlJobRegistry(XxlJobRegistry xxlJobRegistry)
    {
        return xxlJobRegistryMapper.updateById(xxlJobRegistry);
    }

    /**
     * 批量删除注册
     *
     * @param ids 需要删除的注册主键
     * @return 结果
     */
    @Override
    public int deleteXxlJobRegistryByIds(Long[] ids)
    {
        return xxlJobRegistryMapper.deleteXxlJobRegistryByIds(ids);
    }

    /**
     * 删除注册信息
     *
     * @param id 注册主键
     * @return 结果
     */
    @Override
    public int deleteXxlJobRegistryById(Long id)
    {
        return xxlJobRegistryMapper.deleteById(id);
    }
}
