package com.hcp.operator.service;

import java.util.HashMap;
import java.util.List;
import com.hcp.operator.domain.CustomPrice;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hcp.system.api.domain.Bo.FeeRangeTime;
import com.hcp.system.api.domain.vo.PriceVO;

/**
 * 价格Service接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface ICustomPriceService
{
    /**
     * 查询价格
     *
     * @param priceId 价格主键
     * @return 价格
     */
    CustomPrice selectCustomPriceByPriceId(Long priceId);

    /**
     * 查询价格列表-分页
     *
     * @param customPrice 价格
     * @return 价格集合
     */
    IPage<CustomPrice> selectCustomPricePage(CustomPrice customPrice);
    /**
     * 查询价格列表
     *
     * @param customPrice 价格
     * @return 价格集合
     */
    List<CustomPrice> selectCustomPriceList(CustomPrice customPrice);

    /**
     * 新增价格
     *
     * @param customPrice 价格
     * @return 结果
     */
    int insertCustomPrice(CustomPrice customPrice);

    /**
     * 修改价格
     *
     * @param customPrice 价格
     * @return 结果
     */
    int updateCustomPrice(CustomPrice customPrice);

    /**
     * 批量删除价格
     *
     * @param priceIds 需要删除的价格主键集合
     * @return 结果
     */
    int deleteCustomPriceByPriceIds(Long[] priceIds);

    /**
     * 删除价格信息
     *
     * @param priceId 价格主键
     * @return 结果
     */
    int deleteCustomPriceByPriceId(Long priceId);

    List<FeeRangeTime> getPriceByPileId(String pileId);

    List<PriceVO> queryPriceByPlotId(HashMap<String, String> map);
}
