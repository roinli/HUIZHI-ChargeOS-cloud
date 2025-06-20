package com.hcp.operator.mapper;

import java.util.List;
import com.hcp.operator.domain.HlhtStationInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 站点信息Mapper接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface HlhtStationInfoMapper extends BaseMapperX<HlhtStationInfo>
{

    /**
     * 查询站点信息列表-分页
     *
     * @param hlhtStationInfo 站点信息
     * @return 站点信息集合
     */
    IPage<HlhtStationInfo> selectHlhtStationInfoList(Page page,@Param("query") HlhtStationInfo hlhtStationInfo);
    /**
     * 查询站点信息列表
     *
     * @param hlhtStationInfo 站点信息
     * @return 站点信息集合
     */
    List<HlhtStationInfo> selectHlhtStationInfoList(@Param("query") HlhtStationInfo hlhtStationInfo);

    /**
     * 批量删除站点信息
     *
     * @param stationIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteHlhtStationInfoByStationIds(String[] stationIds);
}
