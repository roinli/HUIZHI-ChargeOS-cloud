package com.hcp.job.service;

import java.util.List;
import com.hcp.job.domain.XxlJobLogReport;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 报告Service接口
 *
 * @author vctgo
 * @date 2023-01-03
 */
public interface IXxlJobLogReportService
{
    /**
     * 查询报告
     *
     * @param id 报告主键
     * @return 报告
     */
    XxlJobLogReport selectXxlJobLogReportById(Long id);

    /**
     * 查询报告列表-分页
     *
     * @param xxlJobLogReport 报告
     * @return 报告集合
     */
    IPage<XxlJobLogReport> selectXxlJobLogReportPage(XxlJobLogReport xxlJobLogReport);
    /**
     * 查询报告列表
     *
     * @param xxlJobLogReport 报告
     * @return 报告集合
     */
    List<XxlJobLogReport> selectXxlJobLogReportList(XxlJobLogReport xxlJobLogReport);

    /**
     * 新增报告
     *
     * @param xxlJobLogReport 报告
     * @return 结果
     */
    int insertXxlJobLogReport(XxlJobLogReport xxlJobLogReport);

    /**
     * 修改报告
     *
     * @param xxlJobLogReport 报告
     * @return 结果
     */
    int updateXxlJobLogReport(XxlJobLogReport xxlJobLogReport);

    /**
     * 批量删除报告
     *
     * @param ids 需要删除的报告主键集合
     * @return 结果
     */
    int deleteXxlJobLogReportByIds(Long[] ids);

    /**
     * 删除报告信息
     *
     * @param id 报告主键
     * @return 结果
     */
    int deleteXxlJobLogReportById(Long id);
}
