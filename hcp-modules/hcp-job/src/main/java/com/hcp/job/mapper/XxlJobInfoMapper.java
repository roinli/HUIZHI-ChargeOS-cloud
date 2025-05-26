package com.hcp.job.mapper;

import java.util.List;
import com.hcp.job.domain.XxlJobInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 任务Mapper接口
 *
 * @author vctgo
 * @date 2022-12-31
 */
public interface XxlJobInfoMapper extends BaseMapperX<XxlJobInfo>
{

    /**
     * 查询任务列表-分页
     *
     * @param xxlJobInfo 任务
     * @return 任务集合
     */
    IPage<XxlJobInfo> selectXxlJobInfoList(Page page,@Param("query") XxlJobInfo xxlJobInfo);
    /**
     * 查询任务列表
     *
     * @param xxlJobInfo 任务
     * @return 任务集合
     */
    List<XxlJobInfo> selectXxlJobInfoList(@Param("query") XxlJobInfo xxlJobInfo);

    /**
     * 批量删除任务
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteXxlJobInfoByIds(Long[] ids);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    XxlJobInfo getById(Integer id);

    XxlJobInfo loadById(@Param("id") int id);

    List<XxlJobInfo> scheduleJobQuery(@Param("maxNextTime") long maxNextTime, @Param("pagesize") int pagesize );

    int scheduleUpdate(XxlJobInfo xxlJobInfo);

    int updateOne(XxlJobInfo xxlJobInfo);

    void save(XxlJobInfo jobInfo);
}
