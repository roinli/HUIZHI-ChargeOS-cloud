package com.hcp.file.service;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.file.domain.SysOssConfig;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 对象存储Service接口
 *
 * @author hcp
 * @date 2024-08-07
 */
public interface ISysOssConfigService
{
    /**
     * 查询对象存储
     *
     * @param id 对象存储主键
     * @return 对象存储
     */
    SysOssConfig selectSysOssConfigById(Long id);

    /**
     * 查询对象存储列表-分页
     *
     * @param sysOssConfig 对象存储
     * @return 对象存储集合
     */
    IPage<SysOssConfig> selectSysOssConfigPage(SysOssConfig sysOssConfig);
    /**
     * 查询对象存储列表
     *
     * @param sysOssConfig 对象存储
     * @return 对象存储集合
     */
    List<SysOssConfig> selectSysOssConfigList(SysOssConfig sysOssConfig);

    /**
     * 新增对象存储
     *
     * @param sysOssConfig 对象存储
     * @return 结果
     */
    int insertSysOssConfig(SysOssConfig sysOssConfig);

    /**
     * 修改对象存储
     *
     * @param sysOssConfig 对象存储
     * @return 结果
     */
    int updateSysOssConfig(SysOssConfig sysOssConfig);

    /**
     * 批量删除对象存储
     *
     * @param ids 需要删除的对象存储主键集合
     * @return 结果
     */
    int deleteSysOssConfigByIds(Long[] ids);

    /**
     * 删除对象存储信息
     *
     * @param id 对象存储主键
     * @return 结果
     */
    int deleteSysOssConfigById(Long id);

    List<SysOssConfig> list();


    SysOssConfig selectDefaultOssConfig(Long tenantId);
}
