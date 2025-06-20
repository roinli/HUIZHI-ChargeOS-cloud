package com.hcp.operator.service;

import java.util.List;
import com.hcp.operator.domain.HlhtEquipmentInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 设备列表Service接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface IHlhtEquipmentInfoService
{
    /**
     * 查询设备列表
     *
     * @param equipmentID 设备列表主键
     * @return 设备列表
     */
    HlhtEquipmentInfo selectHlhtEquipmentInfoByEquipmentID(String equipmentID);

    /**
     * 查询设备列表列表-分页
     *
     * @param hlhtEquipmentInfo 设备列表
     * @return 设备列表集合
     */
    IPage<HlhtEquipmentInfo> selectHlhtEquipmentInfoPage(HlhtEquipmentInfo hlhtEquipmentInfo);
    /**
     * 查询设备列表列表
     *
     * @param hlhtEquipmentInfo 设备列表
     * @return 设备列表集合
     */
    List<HlhtEquipmentInfo> selectHlhtEquipmentInfoList(HlhtEquipmentInfo hlhtEquipmentInfo);

    /**
     * 新增设备列表
     *
     * @param hlhtEquipmentInfo 设备列表
     * @return 结果
     */
    int insertHlhtEquipmentInfo(HlhtEquipmentInfo hlhtEquipmentInfo);

    /**
     * 修改设备列表
     *
     * @param hlhtEquipmentInfo 设备列表
     * @return 结果
     */
    int updateHlhtEquipmentInfo(HlhtEquipmentInfo hlhtEquipmentInfo);

    /**
     * 批量删除设备列表
     *
     * @param equipmentIDs 需要删除的设备列表主键集合
     * @return 结果
     */
    int deleteHlhtEquipmentInfoByEquipmentIDs(String[] equipmentIDs);

    /**
     * 删除设备列表信息
     *
     * @param equipmentID 设备列表主键
     * @return 结果
     */
    int deleteHlhtEquipmentInfoByEquipmentID(String equipmentID);
}
