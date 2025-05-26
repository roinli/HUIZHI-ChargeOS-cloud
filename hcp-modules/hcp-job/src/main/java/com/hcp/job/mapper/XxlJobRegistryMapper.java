package com.hcp.job.mapper;

import java.util.Date;
import java.util.List;
import com.hcp.job.domain.XxlJobRegistry;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 注册Mapper接口
 *
 * @author vctgo
 * @date 2023-01-03
 */
public interface XxlJobRegistryMapper extends BaseMapperX<XxlJobRegistry>
{

    /**
     * 查询注册列表-分页
     *
     * @param xxlJobRegistry 注册
     * @return 注册集合
     */
    IPage<XxlJobRegistry> selectXxlJobRegistryList(Page page,@Param("query") XxlJobRegistry xxlJobRegistry);
    /**
     * 查询注册列表
     *
     * @param xxlJobRegistry 注册
     * @return 注册集合
     */
    List<XxlJobRegistry> selectXxlJobRegistryList(@Param("query") XxlJobRegistry xxlJobRegistry);

    /**
     * 批量删除注册
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteXxlJobRegistryByIds(Long[] ids);

    List<Integer> findDead(@Param("timeout") int timeout,
                           @Param("nowTime") Date nowTime);

    int removeDead(@Param("ids") List<Integer> ids);

    List<XxlJobRegistry> findAll(@Param("timeout") int timeout,
                                 @Param("nowTime") Date nowTime);

    int registryUpdate(@Param("registryGroup") String registryGroup,
                       @Param("registryKey") String registryKey,
                       @Param("registryValue") String registryValue,
                       @Param("updateTime") Date updateTime);

    int registrySave(@Param("registryGroup") String registryGroup,
                     @Param("registryKey") String registryKey,
                     @Param("registryValue") String registryValue,
                     @Param("updateTime") Date updateTime);

    int registryDelete(@Param("registryGroup") String registryGroup,
                       @Param("registryKey") String registryKey,
                       @Param("registryValue") String registryValue);
}
