package com.hcp.job.mapper;

import java.util.List;
import com.hcp.job.domain.XxlJobLogReport;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 报告Mapper接口
 *
 * @author vctgo
 * @date 2023-01-03
 */
public interface XxlJobLogReportMapper extends BaseMapperX<XxlJobLogReport>
{

    /**
     * 查询报告列表-分页
     *
     * @param xxlJobLogReport 报告
     * @return 报告集合
     */
    IPage<XxlJobLogReport> selectXxlJobLogReportList(Page page,@Param("query") XxlJobLogReport xxlJobLogReport);
    /**
     * 查询报告列表
     *
     * @param xxlJobLogReport 报告
     * @return 报告集合
     */
    List<XxlJobLogReport> selectXxlJobLogReportList(@Param("query") XxlJobLogReport xxlJobLogReport);

    /**
     * 批量删除报告
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteXxlJobLogReportByIds(Long[] ids);

    int update(XxlJobLogReport xxlJobLogReport);

    int save(XxlJobLogReport xxlJobLogReport);

}
