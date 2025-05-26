package com.hcp.operator.mapper;

import java.util.List;
import com.hcp.operator.domain.HlhtChargeOrderInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 充电站充电订单信息Mapper接口
 *
 * @author hcp
 * @date 2024-08-11
 */
public interface HlhtChargeOrderInfoMapper extends BaseMapperX<HlhtChargeOrderInfo>
{

    /**
     * 查询充电站充电订单信息列表-分页
     *
     * @param hlhtChargeOrderInfo 充电站充电订单信息
     * @return 充电站充电订单信息集合
     */
    IPage<HlhtChargeOrderInfo> selectHlhtChargeOrderInfoList(Page page,@Param("query") HlhtChargeOrderInfo hlhtChargeOrderInfo);
    /**
     * 查询充电站充电订单信息列表
     *
     * @param hlhtChargeOrderInfo 充电站充电订单信息
     * @return 充电站充电订单信息集合
     */
    List<HlhtChargeOrderInfo> selectHlhtChargeOrderInfoList(@Param("query") HlhtChargeOrderInfo hlhtChargeOrderInfo);

    /**
     * 批量删除充电站充电订单信息
     *
     * @param orderNos 需要删除的数据主键集合
     * @return 结果
     */
    int deleteHlhtChargeOrderInfoByOrderNos(String[] orderNos);
}
