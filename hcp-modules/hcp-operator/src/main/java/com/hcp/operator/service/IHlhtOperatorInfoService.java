package com.hcp.operator.service;

import java.util.List;
import com.hcp.operator.domain.HlhtOperatorInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 运营商信息Service接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface IHlhtOperatorInfoService
{
    /**
     * 查询运营商信息
     *
     * @param operatorId 运营商信息主键
     * @return 运营商信息
     */
    HlhtOperatorInfo selectHlhtOperatorInfoByOperatorId(String operatorId);

    /**
     * 查询运营商信息列表-分页
     *
     * @param hlhtOperatorInfo 运营商信息
     * @return 运营商信息集合
     */
    IPage<HlhtOperatorInfo> selectHlhtOperatorInfoPage(HlhtOperatorInfo hlhtOperatorInfo);
    /**
     * 查询运营商信息列表
     *
     * @param hlhtOperatorInfo 运营商信息
     * @return 运营商信息集合
     */
    List<HlhtOperatorInfo> selectHlhtOperatorInfoList(HlhtOperatorInfo hlhtOperatorInfo);

    /**
     * 新增运营商信息
     *
     * @param hlhtOperatorInfo 运营商信息
     * @return 结果
     */
    int insertHlhtOperatorInfo(HlhtOperatorInfo hlhtOperatorInfo);

    /**
     * 修改运营商信息
     *
     * @param hlhtOperatorInfo 运营商信息
     * @return 结果
     */
    int updateHlhtOperatorInfo(HlhtOperatorInfo hlhtOperatorInfo);

    /**
     * 批量删除运营商信息
     *
     * @param operatorIds 需要删除的运营商信息主键集合
     * @return 结果
     */
    int deleteHlhtOperatorInfoByOperatorIds(String[] operatorIds);

    /**
     * 删除运营商信息信息
     *
     * @param operatorId 运营商信息主键
     * @return 结果
     */
    int deleteHlhtOperatorInfoByOperatorId(String operatorId);
}
