package com.hcp.file.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hcp.file.domain.SysOssConfig;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 对象存储Mapper接口
 *
 * @author hcp
 * @date 2024-08-07
 */
public interface SysOssConfigMapper extends BaseMapper<SysOssConfig>
{

    /**
     * 查询对象存储列表-分页
     *
     * @param sysOssConfig 对象存储
     * @return 对象存储集合
     */
    IPage<SysOssConfig> selectSysOssConfigList(Page page,@Param("query") SysOssConfig sysOssConfig);
    /**
     * 查询对象存储列表
     *
     * @param sysOssConfig 对象存储
     * @return 对象存储集合
     */
    List<SysOssConfig> selectSysOssConfigList(@Param("query") SysOssConfig sysOssConfig);

    /**
     * 批量删除对象存储
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysOssConfigByIds(Long[] ids);

    @InterceptorIgnore(tenantLine = "1")
    List<SysOssConfig> allSysOssConfigList();
}
