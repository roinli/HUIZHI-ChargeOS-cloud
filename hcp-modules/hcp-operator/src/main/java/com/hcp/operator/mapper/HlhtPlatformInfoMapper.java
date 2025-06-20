package com.hcp.operator.mapper;

import java.util.List;
import com.hcp.operator.domain.HlhtPlatformInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 平台信息Mapper接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface HlhtPlatformInfoMapper extends BaseMapperX<HlhtPlatformInfo>
{

    /**
     * 查询平台信息列表-分页
     *
     * @param hlhtPlatformInfo 平台信息
     * @return 平台信息集合
     */
    IPage<HlhtPlatformInfo> selectHlhtPlatformInfoList(Page page,@Param("query") HlhtPlatformInfo hlhtPlatformInfo);
    /**
     * 查询平台信息列表
     *
     * @param hlhtPlatformInfo 平台信息
     * @return 平台信息集合
     */
    List<HlhtPlatformInfo> selectHlhtPlatformInfoList(@Param("query") HlhtPlatformInfo hlhtPlatformInfo);

    /**
     * 批量删除平台信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteHlhtPlatformInfoByIds(Long[] ids);
}
