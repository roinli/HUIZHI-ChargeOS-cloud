package com.hcp.operator.mapper;

import java.util.List;
import com.hcp.operator.domain.HlhtOperatorInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 运营商信息Mapper接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface HlhtOperatorInfoMapper extends BaseMapperX<HlhtOperatorInfo>
{

    /**
     * 查询运营商信息列表-分页
     *
     * @param hlhtOperatorInfo 运营商信息
     * @return 运营商信息集合
     */
    IPage<HlhtOperatorInfo> selectHlhtOperatorInfoList(Page page,@Param("query") HlhtOperatorInfo hlhtOperatorInfo);
    /**
     * 查询运营商信息列表
     *
     * @param hlhtOperatorInfo 运营商信息
     * @return 运营商信息集合
     */
    List<HlhtOperatorInfo> selectHlhtOperatorInfoList(@Param("query") HlhtOperatorInfo hlhtOperatorInfo);

    /**
     * 批量删除运营商信息
     *
     * @param operatorIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteHlhtOperatorInfoByOperatorIds(String[] operatorIds);
}
