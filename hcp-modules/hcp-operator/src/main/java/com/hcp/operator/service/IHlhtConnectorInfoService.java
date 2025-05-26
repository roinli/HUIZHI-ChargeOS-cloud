package com.hcp.operator.service;

import java.util.List;
import com.hcp.operator.domain.HlhtConnectorInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 接口信息Service接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface IHlhtConnectorInfoService
{
    /**
     * 查询接口信息
     *
     * @param connectorId 接口信息主键
     * @return 接口信息
     */
    HlhtConnectorInfo selectHlhtConnectorInfoByConnectorId(String connectorId);

    /**
     * 查询接口信息列表-分页
     *
     * @param hlhtConnectorInfo 接口信息
     * @return 接口信息集合
     */
    IPage<HlhtConnectorInfo> selectHlhtConnectorInfoPage(HlhtConnectorInfo hlhtConnectorInfo);
    /**
     * 查询接口信息列表
     *
     * @param hlhtConnectorInfo 接口信息
     * @return 接口信息集合
     */
    List<HlhtConnectorInfo> selectHlhtConnectorInfoList(HlhtConnectorInfo hlhtConnectorInfo);

    /**
     * 新增接口信息
     *
     * @param hlhtConnectorInfo 接口信息
     * @return 结果
     */
    int insertHlhtConnectorInfo(HlhtConnectorInfo hlhtConnectorInfo);

    /**
     * 修改接口信息
     *
     * @param hlhtConnectorInfo 接口信息
     * @return 结果
     */
    int updateHlhtConnectorInfo(HlhtConnectorInfo hlhtConnectorInfo);

    /**
     * 批量删除接口信息
     *
     * @param connectorIds 需要删除的接口信息主键集合
     * @return 结果
     */
    int deleteHlhtConnectorInfoByConnectorIds(String[] connectorIds);

    /**
     * 删除接口信息信息
     *
     * @param connectorId 接口信息主键
     * @return 结果
     */
    int deleteHlhtConnectorInfoByConnectorId(String connectorId);
}
