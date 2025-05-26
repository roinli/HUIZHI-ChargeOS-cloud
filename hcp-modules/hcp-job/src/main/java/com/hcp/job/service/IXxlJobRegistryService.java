package com.hcp.job.service;

import java.util.List;
import com.hcp.job.domain.XxlJobRegistry;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 注册Service接口
 *
 * @author vctgo
 * @date 2023-01-03
 */
public interface IXxlJobRegistryService
{
    /**
     * 查询注册
     *
     * @param id 注册主键
     * @return 注册
     */
    XxlJobRegistry selectXxlJobRegistryById(Long id);

    /**
     * 查询注册列表-分页
     *
     * @param xxlJobRegistry 注册
     * @return 注册集合
     */
    IPage<XxlJobRegistry> selectXxlJobRegistryPage(XxlJobRegistry xxlJobRegistry);
    /**
     * 查询注册列表
     *
     * @param xxlJobRegistry 注册
     * @return 注册集合
     */
    List<XxlJobRegistry> selectXxlJobRegistryList(XxlJobRegistry xxlJobRegistry);

    /**
     * 新增注册
     *
     * @param xxlJobRegistry 注册
     * @return 结果
     */
    int insertXxlJobRegistry(XxlJobRegistry xxlJobRegistry);

    /**
     * 修改注册
     *
     * @param xxlJobRegistry 注册
     * @return 结果
     */
    int updateXxlJobRegistry(XxlJobRegistry xxlJobRegistry);

    /**
     * 批量删除注册
     *
     * @param ids 需要删除的注册主键集合
     * @return 结果
     */
    int deleteXxlJobRegistryByIds(Long[] ids);

    /**
     * 删除注册信息
     *
     * @param id 注册主键
     * @return 结果
     */
    int deleteXxlJobRegistryById(Long id);
}
