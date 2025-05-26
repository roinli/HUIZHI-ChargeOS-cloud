package com.hcp.file.service.impl;

import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.file.mapper.SysOssConfigMapper;
import com.hcp.file.domain.SysOssConfig;
import com.hcp.file.service.ISysOssConfigService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 对象存储Service业务层处理
 *
 * @author hcp
 * @date 2024-08-07
 */
@Service
public class SysOssConfigServiceImpl implements ISysOssConfigService
{
    @Autowired
    private SysOssConfigMapper sysOssConfigMapper;

    /**
     * 查询对象存储
     *
     * @param id 对象存储主键
     * @return 对象存储
     */
    @Override
    public SysOssConfig selectSysOssConfigById(Long id)
    {
        return sysOssConfigMapper.selectById(id);
    }

    /**
     * 查询对象存储列表-分页
     *
     * @param sysOssConfig 对象存储
     * @return 对象存储
     */
    @Override
    public IPage<SysOssConfig> selectSysOssConfigPage(SysOssConfig sysOssConfig)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return sysOssConfigMapper.selectSysOssConfigList(mpPage,sysOssConfig);
    }
    /**
     * 查询对象存储列表
     *
     * @param sysOssConfig 对象存储
     * @return 对象存储
     */
    @Override
    public List<SysOssConfig> selectSysOssConfigList(SysOssConfig sysOssConfig)
    {
        return sysOssConfigMapper.selectSysOssConfigList(sysOssConfig);
    }

    /**
     * 新增对象存储
     *
     * @param sysOssConfig 对象存储
     * @return 结果
     */

    @Override
    public int insertSysOssConfig(SysOssConfig sysOssConfig)
    {
        if (StringUtils.equals(sysOssConfig.getIsDefault(),"Y")){
            List<SysOssConfig> sysOssConfigs = sysOssConfigMapper.selectList(new QueryWrapper<SysOssConfig>().eq("is_default", "Y"));
            sysOssConfigs.forEach(item -> item.setIsDefault("N"));
            sysOssConfigMapper.updateById(sysOssConfigs);
        }
        return sysOssConfigMapper.insert(sysOssConfig);
    }

    /**
     * 修改对象存储
     *
     * @param sysOssConfig 对象存储
     * @return 结果
     */
    @Override
    public int updateSysOssConfig(SysOssConfig sysOssConfig)
    {
        if (StringUtils.equals(sysOssConfig.getIsDefault(),"Y")){
            List<SysOssConfig> sysOssConfigs = sysOssConfigMapper.selectList(new QueryWrapper<SysOssConfig>().eq("is_default", "Y"));
            sysOssConfigs.forEach(item -> item.setIsDefault("N"));
            sysOssConfigMapper.updateById(sysOssConfigs);
        }
        return sysOssConfigMapper.updateById(sysOssConfig);
    }

    /**
     * 批量删除对象存储
     *
     * @param ids 需要删除的对象存储主键
     * @return 结果
     */
    @Override
    public int deleteSysOssConfigByIds(Long[] ids)
    {
        return sysOssConfigMapper.deleteSysOssConfigByIds(ids);
    }

    /**
     * 删除对象存储信息
     *
     * @param id 对象存储主键
     * @return 结果
     */
    @Override
    public int deleteSysOssConfigById(Long id)
    {
        return sysOssConfigMapper.deleteById(id);
    }

    @Override
    public List<SysOssConfig> list() {
        return sysOssConfigMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public SysOssConfig selectDefaultOssConfig(Long tenantId) {
        QueryWrapper<SysOssConfig> ossConfigQueryWrapper = new QueryWrapper<>();
        ossConfigQueryWrapper.eq("tenant_id", tenantId);
        ossConfigQueryWrapper.eq("is_default", "Y");
        ossConfigQueryWrapper.eq("status", 0);
        ossConfigQueryWrapper.last("limit 1");
        return sysOssConfigMapper.selectOne(ossConfigQueryWrapper);
    }
}
