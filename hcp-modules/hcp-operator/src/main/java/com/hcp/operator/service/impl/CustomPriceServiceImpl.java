package com.hcp.operator.service.impl;

import java.math.BigDecimal;
import java.util.*;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.hcp.operator.domain.PriceVo;
import com.hcp.system.api.domain.Bo.FeeRangeTime;
import com.hcp.system.api.domain.vo.PriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.CustomPriceMapper;
import com.hcp.operator.domain.CustomPrice;
import com.hcp.operator.service.ICustomPriceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 价格Service业务层处理
 *
 * @author hcp
 * @date 2024-08-06
 */
@Service
public class CustomPriceServiceImpl implements ICustomPriceService
{
    @Autowired
    private CustomPriceMapper customPriceMapper;

    /**
     * 查询价格
     *
     * @param priceId 价格主键
     * @return 价格
     */
    @Override
    public CustomPrice selectCustomPriceByPriceId(Long priceId)
    {
        return customPriceMapper.selectById(priceId);
    }

    /**
     * 查询价格列表-分页
     *
     * @param customPrice 价格
     * @return 价格
     */
    @Override
    public IPage<CustomPrice> selectCustomPricePage(CustomPrice customPrice)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return customPriceMapper.selectCustomPriceList(mpPage,customPrice);
    }
    /**
     * 查询价格列表
     *
     * @param customPrice 价格
     * @return 价格
     */
    @Override
    public List<CustomPrice> selectCustomPriceList(CustomPrice customPrice)
    {
        return customPriceMapper.selectCustomPriceList(customPrice);
    }

    /**
     * 新增价格
     *
     * @param customPrice 价格
     * @return 结果
     */

    @Override
    public int insertCustomPrice(CustomPrice customPrice)
    {
        return customPriceMapper.insert(customPrice);
    }

    /**
     * 修改价格
     *
     * @param customPrice 价格
     * @return 结果
     */
    @Override
    public int updateCustomPrice(CustomPrice customPrice)
    {
        return customPriceMapper.updateById(customPrice);
    }

    /**
     * 批量删除价格
     *
     * @param priceIds 需要删除的价格主键
     * @return 结果
     */
    @Override
    public int deleteCustomPriceByPriceIds(Long[] priceIds)
    {
        return customPriceMapper.deleteCustomPriceByPriceIds(priceIds);
    }

    /**
     * 删除价格信息
     *
     * @param priceId 价格主键
     * @return 结果
     */
    @Override
    public int deleteCustomPriceByPriceId(Long priceId)
    {
        return customPriceMapper.deleteById(priceId);
    }

    @Override
    public List<FeeRangeTime> getPriceByPileId(String pileId) {
        List<CustomPrice> customPrices = customPriceMapper.getPilePrice(pileId);
        if (CollUtil.isNotEmpty(customPrices)) {
            customPrices.sort(Comparator.comparing(CustomPrice::getPriceType));
            // 时间段信息
            List<FeeRangeTime> rangeTimeList = new ArrayList<>();
            for (CustomPrice customPrice : customPrices) {
                String rangeTime = customPrice.getRangTime();
                List<PriceVo.TimeRange> rangeTimes = JSONUtil.toList(rangeTime, PriceVo.TimeRange.class);
                for (PriceVo.TimeRange time : rangeTimes) {
                    FeeRangeTime feeRangeTime = new FeeRangeTime();
                    BeanUtil.copyProperties(time, feeRangeTime);
                    feeRangeTime.setPriceType(customPrice.getPriceType().byteValue());
                    rangeTimeList.add(feeRangeTime);
                }
            }
            // 按startTime排序rangeTimeList
            rangeTimeList.sort(Comparator.comparing(FeeRangeTime::getStartTime));
            // 计算48个时间段每个时间段的价格编号
            return rangeTimeList;
        }
        return Collections.emptyList();
    }

    @Override
    public List<PriceVO> queryPriceByPlotId(HashMap<String, String> map) {
        return customPriceMapper.queryPriceByPlotId(map);
    }
}
