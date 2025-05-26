package com.hcp.operator.service;

import java.util.List;
import com.hcp.operator.domain.HlhtPlatformInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 平台信息Service接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface IHlhtPlatformInfoService
{
    /**
     * 查询平台信息
     *
     * @param id 平台信息主键
     * @return 平台信息
     */
    HlhtPlatformInfo selectHlhtPlatformInfoById(Long id);

    /**
     * 查询平台信息列表-分页
     *
     * @param hlhtPlatformInfo 平台信息
     * @return 平台信息集合
     */
    IPage<HlhtPlatformInfo> selectHlhtPlatformInfoPage(HlhtPlatformInfo hlhtPlatformInfo);
    /**
     * 查询平台信息列表
     *
     * @param hlhtPlatformInfo 平台信息
     * @return 平台信息集合
     */
    List<HlhtPlatformInfo> selectHlhtPlatformInfoList(HlhtPlatformInfo hlhtPlatformInfo);

    /**
     * 新增平台信息
     *
     * @param hlhtPlatformInfo 平台信息
     * @return 结果
     */
    int insertHlhtPlatformInfo(HlhtPlatformInfo hlhtPlatformInfo);

    /**
     * 修改平台信息
     *
     * @param hlhtPlatformInfo 平台信息
     * @return 结果
     */
    int updateHlhtPlatformInfo(HlhtPlatformInfo hlhtPlatformInfo);

    /**
     * 批量删除平台信息
     *
     * @param ids 需要删除的平台信息主键集合
     * @return 结果
     */
    int deleteHlhtPlatformInfoByIds(Long[] ids);

    /**
     * 删除平台信息信息
     *
     * @param id 平台信息主键
     * @return 结果
     */
    int deleteHlhtPlatformInfoById(Long id);
}
