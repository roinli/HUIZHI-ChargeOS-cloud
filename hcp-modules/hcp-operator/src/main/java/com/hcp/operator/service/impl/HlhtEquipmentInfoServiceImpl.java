package com.hcp.operator.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.HlhtEquipmentInfoMapper;
import com.hcp.operator.domain.HlhtEquipmentInfo;
import com.hcp.operator.service.IHlhtEquipmentInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 设备列表Service业务层处理
 *
 * @author hcp
 * @date 2024-08-11
 */
@Service
public class HlhtEquipmentInfoServiceImpl implements IHlhtEquipmentInfoService
{
    @Autowired
    private HlhtEquipmentInfoMapper hlhtEquipmentInfoMapper;

    /**
     * 查询设备列表
     *
     * @param equipmentID 设备列表主键
     * @return 设备列表
     */
    @Override
    public HlhtEquipmentInfo selectHlhtEquipmentInfoByEquipmentID(String equipmentID)
    {
        return hlhtEquipmentInfoMapper.selectById(equipmentID);
    }

    /**
     * 查询设备列表列表-分页
     *
     * @param hlhtEquipmentInfo 设备列表
     * @return 设备列表
     */
    @Override
    public IPage<HlhtEquipmentInfo> selectHlhtEquipmentInfoPage(HlhtEquipmentInfo hlhtEquipmentInfo)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return hlhtEquipmentInfoMapper.selectHlhtEquipmentInfoList(mpPage,hlhtEquipmentInfo);
    }
    /**
     * 查询设备列表列表
     *
     * @param hlhtEquipmentInfo 设备列表
     * @return 设备列表
     */
    @Override
    public List<HlhtEquipmentInfo> selectHlhtEquipmentInfoList(HlhtEquipmentInfo hlhtEquipmentInfo)
    {
        return hlhtEquipmentInfoMapper.selectHlhtEquipmentInfoList(hlhtEquipmentInfo);
    }

    /**
     * 新增设备列表
     *
     * @param hlhtEquipmentInfo 设备列表
     * @return 结果
     */

    @Override
    public int insertHlhtEquipmentInfo(HlhtEquipmentInfo hlhtEquipmentInfo)
    {
        return hlhtEquipmentInfoMapper.insert(hlhtEquipmentInfo);
    }

    /**
     * 修改设备列表
     *
     * @param hlhtEquipmentInfo 设备列表
     * @return 结果
     */
    @Override
    public int updateHlhtEquipmentInfo(HlhtEquipmentInfo hlhtEquipmentInfo)
    {
        return hlhtEquipmentInfoMapper.updateById(hlhtEquipmentInfo);
    }

    /**
     * 批量删除设备列表
     *
     * @param equipmentIDs 需要删除的设备列表主键
     * @return 结果
     */
    @Override
    public int deleteHlhtEquipmentInfoByEquipmentIDs(String[] equipmentIDs)
    {
        return hlhtEquipmentInfoMapper.deleteHlhtEquipmentInfoByEquipmentIDs(equipmentIDs);
    }

    /**
     * 删除设备列表信息
     *
     * @param equipmentID 设备列表主键
     * @return 结果
     */
    @Override
    public int deleteHlhtEquipmentInfoByEquipmentID(String equipmentID)
    {
        return hlhtEquipmentInfoMapper.deleteById(equipmentID);
    }
}
