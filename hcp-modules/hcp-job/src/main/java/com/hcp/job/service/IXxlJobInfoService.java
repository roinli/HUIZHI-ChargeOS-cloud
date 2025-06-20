package com.hcp.job.service;

import java.util.List;

import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.job.domain.XxlJobInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 任务Service接口
 *
 * @author vctgo
 * @date 2022-12-31
 */
public interface IXxlJobInfoService
{
    /**
     * 查询任务
     *
     * @param id 任务主键
     * @return 任务
     */
    XxlJobInfo selectXxlJobInfoById(Integer id);

    /**
     * 查询任务列表-分页
     *
     * @param xxlJobInfo 任务
     * @return 任务集合
     */
    IPage<XxlJobInfo> selectXxlJobInfoPage(XxlJobInfo xxlJobInfo);
    /**
     * 查询任务列表
     *
     * @param xxlJobInfo 任务
     * @return 任务集合
     */
    List<XxlJobInfo> selectXxlJobInfoList(XxlJobInfo xxlJobInfo);

    /**
     * 新增任务
     *
     * @param xxlJobInfo 任务
     * @return 结果
     */
    int insertXxlJobInfo(XxlJobInfo xxlJobInfo);

    /**
     * 修改任务
     *
     * @param xxlJobInfo 任务
     * @return 结果
     */
    int updateXxlJobInfo(XxlJobInfo xxlJobInfo);

    /**
     * 批量删除任务
     *
     * @param ids 需要删除的任务主键集合
     * @return 结果
     */
    int deleteXxlJobInfoByIds(Long[] ids);

    /**
     * 删除任务信息
     *
     * @param id 任务主键
     * @return 结果
     */
    int deleteXxlJobInfoById(Long id);

    /**
     * 开启任务
     * @param id
     * @return
     */
    AjaxResult start(Integer id);

    /**
     * 停止任务
     * @param id
     * @return
     */
    AjaxResult stop(Integer id);

    /**
     * 新增任务
     * @param xxlJobInfo
     * @return
     */
    AjaxResult add(XxlJobInfo xxlJobInfo);
}
