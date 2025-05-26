package com.hcp.job.mapper;

import java.util.List;
import com.hcp.job.domain.XxlJobGroup;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 定时任务分组Mapper接口
 *
 * @author vctgo
 * @date 2023-01-01
 */
public interface XxlJobGroupMapper extends BaseMapperX<XxlJobGroup>
{

    /**
     * 查询定时任务分组列表-分页
     *
     * @param xxlJobGroup 定时任务分组
     * @return 定时任务分组集合
     */
    IPage<XxlJobGroup> selectXxlJobGroupList(Page page,@Param("query") XxlJobGroup xxlJobGroup);
    /**
     * 查询定时任务分组列表
     *
     * @param xxlJobGroup 定时任务分组
     * @return 定时任务分组集合
     */
    List<XxlJobGroup> selectXxlJobGroupList(@Param("query") XxlJobGroup xxlJobGroup);

    /**
     * 批量删除定时任务分组
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteXxlJobGroupByIds(Long[] ids);

    /**
     * 根据ID查询
     * @param jobGroup
     * @return
     */
    XxlJobGroup load(Integer jobGroup);

    List<XxlJobGroup> findByAddressType(@Param("addressType") int addressType);

    void update(XxlJobGroup group);

    XxlJobGroup getById(Long id);
}
