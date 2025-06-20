package com.hcp.operator.mapper;

import java.util.List;
import com.hcp.operator.domain.HlhtEquipmentInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 设备列表Mapper接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface HlhtEquipmentInfoMapper extends BaseMapperX<HlhtEquipmentInfo>
{

    /**
     * 查询设备列表列表-分页
     *
     * @param hlhtEquipmentInfo 设备列表
     * @return 设备列表集合
     */
    IPage<HlhtEquipmentInfo> selectHlhtEquipmentInfoList(Page page,@Param("query") HlhtEquipmentInfo hlhtEquipmentInfo);
    /**
     * 查询设备列表列表
     *
     * @param hlhtEquipmentInfo 设备列表
     * @return 设备列表集合
     */
    List<HlhtEquipmentInfo> selectHlhtEquipmentInfoList(@Param("query") HlhtEquipmentInfo hlhtEquipmentInfo);

    /**
     * 批量删除设备列表
     *
     * @param equipmentIDs 需要删除的数据主键集合
     * @return 结果
     */
    int deleteHlhtEquipmentInfoByEquipmentIDs(String[] equipmentIDs);
}
