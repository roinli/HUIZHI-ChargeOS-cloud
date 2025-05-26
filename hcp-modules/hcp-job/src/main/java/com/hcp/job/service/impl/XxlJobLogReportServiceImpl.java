package com.hcp.job.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.job.mapper.XxlJobLogReportMapper;
import com.hcp.job.domain.XxlJobLogReport;
import com.hcp.job.service.IXxlJobLogReportService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 报告Service业务层处理
 *
 * @author vctgo
 * @date 2023-01-03
 */
@Service
public class XxlJobLogReportServiceImpl implements IXxlJobLogReportService
{
    @Autowired
    private XxlJobLogReportMapper xxlJobLogReportMapper;

    /**
     * 查询报告
     *
     * @param id 报告主键
     * @return 报告
     */
    @Override
    public XxlJobLogReport selectXxlJobLogReportById(Long id)
    {
        return xxlJobLogReportMapper.selectById(id);
    }

    /**
     * 查询报告列表-分页
     *
     * @param xxlJobLogReport 报告
     * @return 报告
     */
    @Override
    public IPage<XxlJobLogReport> selectXxlJobLogReportPage(XxlJobLogReport xxlJobLogReport)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return xxlJobLogReportMapper.selectXxlJobLogReportList(mpPage,xxlJobLogReport);
    }
    /**
     * 查询报告列表
     *
     * @param xxlJobLogReport 报告
     * @return 报告
     */
    @Override
    public List<XxlJobLogReport> selectXxlJobLogReportList(XxlJobLogReport xxlJobLogReport)
    {
        return xxlJobLogReportMapper.selectXxlJobLogReportList(xxlJobLogReport);
    }

    /**
     * 新增报告
     *
     * @param xxlJobLogReport 报告
     * @return 结果
     */

    @Override
    public int insertXxlJobLogReport(XxlJobLogReport xxlJobLogReport)
    {
        return xxlJobLogReportMapper.insert(xxlJobLogReport);
    }

    /**
     * 修改报告
     *
     * @param xxlJobLogReport 报告
     * @return 结果
     */
    @Override
    public int updateXxlJobLogReport(XxlJobLogReport xxlJobLogReport)
    {
        return xxlJobLogReportMapper.updateById(xxlJobLogReport);
    }

    /**
     * 批量删除报告
     *
     * @param ids 需要删除的报告主键
     * @return 结果
     */
    @Override
    public int deleteXxlJobLogReportByIds(Long[] ids)
    {
        return xxlJobLogReportMapper.deleteXxlJobLogReportByIds(ids);
    }

    /**
     * 删除报告信息
     *
     * @param id 报告主键
     * @return 结果
     */
    @Override
    public int deleteXxlJobLogReportById(Long id)
    {
        return xxlJobLogReportMapper.deleteById(id);
    }
}
