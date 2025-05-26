package com.hcp.job.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.job.mapper.XxlJobGroupMapper;
import com.hcp.job.domain.XxlJobGroup;
import com.hcp.job.service.IXxlJobGroupService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 定时任务分组Service业务层处理
 *
 * @author vctgo
 */
@Service
public class XxlJobGroupServiceImpl implements IXxlJobGroupService
{
    @Autowired
    private XxlJobGroupMapper xxlJobGroupMapper;

    /**
     * 查询定时任务分组
     *
     * @param id 定时任务分组主键
     * @return 定时任务分组
     */
    @Override
    public XxlJobGroup selectXxlJobGroupById(Long id)
    {
        return xxlJobGroupMapper.selectById(id);
    }

    /**
     * 查询定时任务分组列表-分页
     *
     * @param xxlJobGroup 定时任务分组
     * @return 定时任务分组
     */
    @Override
    public IPage<XxlJobGroup> selectXxlJobGroupPage(XxlJobGroup xxlJobGroup)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return xxlJobGroupMapper.selectXxlJobGroupList(mpPage,xxlJobGroup);
    }
    /**
     * 查询定时任务分组列表
     *
     * @param xxlJobGroup 定时任务分组
     * @return 定时任务分组
     */
    @Override
    public List<XxlJobGroup> selectXxlJobGroupList(XxlJobGroup xxlJobGroup)
    {
        return xxlJobGroupMapper.selectXxlJobGroupList(xxlJobGroup);
    }

    /**
     * 新增定时任务分组
     *
     * @param xxlJobGroup 定时任务分组
     * @return 结果
     */

    @Override
    public int insertXxlJobGroup(XxlJobGroup xxlJobGroup)
    {
        return xxlJobGroupMapper.insert(xxlJobGroup);
    }

    /**
     * 修改定时任务分组
     *
     * @param xxlJobGroup 定时任务分组
     * @return 结果
     */
    @Override
    public int updateXxlJobGroup(XxlJobGroup xxlJobGroup)
    {
        xxlJobGroupMapper.update(xxlJobGroup);
        return 1;
    }

    /**
     * 批量删除定时任务分组
     *
     * @param ids 需要删除的定时任务分组主键
     * @return 结果
     */
    @Override
    public int deleteXxlJobGroupByIds(Long[] ids)
    {
        return xxlJobGroupMapper.deleteXxlJobGroupByIds(ids);
    }

    /**
     * 删除定时任务分组信息
     *
     * @param id 定时任务分组主键
     * @return 结果
     */
    @Override
    public int deleteXxlJobGroupById(Long id)
    {
        return xxlJobGroupMapper.deleteById(id);
    }

    /**
     * 根据ID查询
     */
    @Override
    public XxlJobGroup getById(Long id) {
        return xxlJobGroupMapper.getById(id);
    }
}
