package com.hcp.operator.mapper;

import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.operator.domain.CustomPrice;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import com.hcp.system.api.domain.vo.PriceVO;
import org.apache.ibatis.annotations.Param;
/**
 * 价格Mapper接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface CustomPriceMapper extends BaseMapperX<CustomPrice>
{

    /**
     * 查询价格列表-分页
     *
     * @param customPrice 价格
     * @return 价格集合
     */
    IPage<CustomPrice> selectCustomPriceList(Page page,@Param("query") CustomPrice customPrice);
    /**
     * 查询价格列表
     *
     * @param customPrice 价格
     * @return 价格集合
     */
    List<CustomPrice> selectCustomPriceList(@Param("query") CustomPrice customPrice);

    /**
     * 批量删除价格
     *
     * @param priceIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCustomPriceByPriceIds(Long[] priceIds);

    //忽略租户获取价格列表
    @InterceptorIgnore(tenantLine = "1")
    List<CustomPrice> getPilePrice(@Param("pileId") String pileId);
    @InterceptorIgnore(tenantLine = "1")
    List<PriceVO> queryPriceByPlotId(@Param("map") HashMap<String, String> map);
}
