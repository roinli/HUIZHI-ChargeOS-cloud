package com.hcp.job.service;

import java.util.List;
import com.hcp.job.domain.XxlJobGroup;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 定时任务分组Service接口
 *
 * @author vctgo
 * @date 2023-01-01
 */
public interface IXxlJobGroupService
{
    /**
     * 查询定时任务分组
     *
     * @param id 定时任务分组主键
     * @return 定时任务分组
     */
    XxlJobGroup selectXxlJobGroupById(Long id);

    /**
     * 查询定时任务分组列表-分页
     *
     * @param xxlJobGroup 定时任务分组
     * @return 定时任务分组集合
     */
    IPage<XxlJobGroup> selectXxlJobGroupPage(XxlJobGroup xxlJobGroup);
    /**
     * 查询定时任务分组列表
     *
     * @param xxlJobGroup 定时任务分组
     * @return 定时任务分组集合
     */
    List<XxlJobGroup> selectXxlJobGroupList(XxlJobGroup xxlJobGroup);

    /**
     * 新增定时任务分组
     *
     * @param xxlJobGroup 定时任务分组
     * @return 结果
     */
    int insertXxlJobGroup(XxlJobGroup xxlJobGroup);

    /**
     * 修改定时任务分组
     *
     * @param xxlJobGroup 定时任务分组
     * @return 结果
     */
    int updateXxlJobGroup(XxlJobGroup xxlJobGroup);

    /**
     * 批量删除定时任务分组
     *
     * @param ids 需要删除的定时任务分组主键集合
     * @return 结果
     */
    int deleteXxlJobGroupByIds(Long[] ids);

    /**
     * 删除定时任务分组信息
     *
     * @param id 定时任务分组主键
     * @return 结果
     */
    int deleteXxlJobGroupById(Long id);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    XxlJobGroup getById(Long id);
}
