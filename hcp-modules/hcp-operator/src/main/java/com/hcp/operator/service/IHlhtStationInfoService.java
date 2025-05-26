package com.hcp.operator.service;

import java.util.List;
import com.hcp.operator.domain.HlhtStationInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 站点信息Service接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface IHlhtStationInfoService
{
    /**
     * 查询站点信息
     *
     * @param stationId 站点信息主键
     * @return 站点信息
     */
    HlhtStationInfo selectHlhtStationInfoByStationId(String stationId);

    /**
     * 查询站点信息列表-分页
     *
     * @param hlhtStationInfo 站点信息
     * @return 站点信息集合
     */
    IPage<HlhtStationInfo> selectHlhtStationInfoPage(HlhtStationInfo hlhtStationInfo);
    /**
     * 查询站点信息列表
     *
     * @param hlhtStationInfo 站点信息
     * @return 站点信息集合
     */
    List<HlhtStationInfo> selectHlhtStationInfoList(HlhtStationInfo hlhtStationInfo);

    /**
     * 新增站点信息
     *
     * @param hlhtStationInfo 站点信息
     * @return 结果
     */
    int insertHlhtStationInfo(HlhtStationInfo hlhtStationInfo);

    /**
     * 修改站点信息
     *
     * @param hlhtStationInfo 站点信息
     * @return 结果
     */
    int updateHlhtStationInfo(HlhtStationInfo hlhtStationInfo);

    /**
     * 批量删除站点信息
     *
     * @param stationIds 需要删除的站点信息主键集合
     * @return 结果
     */
    int deleteHlhtStationInfoByStationIds(String[] stationIds);

    /**
     * 删除站点信息信息
     *
     * @param stationId 站点信息主键
     * @return 结果
     */
    int deleteHlhtStationInfoByStationId(String stationId);
}
