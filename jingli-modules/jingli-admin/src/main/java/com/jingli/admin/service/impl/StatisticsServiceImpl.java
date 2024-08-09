package com.jingli.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.mapper.OrderMapper;
import com.jingli.admin.mapper.PileMapper;
import com.jingli.admin.model.PilesDetail;
import com.jingli.admin.model.SalesData;
import com.jingli.admin.model.SalesDetail;
import com.jingli.admin.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @描述 统计接口实现类
 * @创建人 yangbin
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    PileMapper pileMapper;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * @描述 获取首页的新增用户数和销售总额
     * @参数 []
     * @返回值 java.util.Map<java.lang.String, java.lang.Object>
     * @创建人 yangbin
     * @创建时间 2019/10/24
     */
    @Override
    public Map<String, Object> getUsersAndSales(Map<String, Object> params) {

        return orderMapper.getUsersAndSales(params);
    }

    /**
     * @描述 获取销售数据
     * @参数 []
     * @返回值 com.jingli.modules.sys.statistics.model.SalesData
     * @创建人 yangbin
     * @创建时间 2019/10/24
     */
    @Override
    public SalesData getSalesData(Map<String, Object> platForm) {
        return orderMapper.getSalesData(platForm);
    }


    /**
     * @描述 获取销售详情
     * @参数 []
     * @返回值 java.util.List<com.jingli.modules.sys.statistics.model.SalesDetail>
     * @创建人 yangbin
     * @创建时间 2019/10/24
     */
    @Override
    public IPage<SalesDetail> getSalesDetail(Page<SalesDetail> page, Map<String, Object> platForm) {
        List<SalesDetail> salesDetails = orderMapper.getSalesDetail(page, platForm);
        page.setRecords(salesDetails);
        return page;
    }


    @Override
    public IPage<PilesDetail> getPilesDetail(Page<PilesDetail> page, Map<String, Object> params) {
        List<PilesDetail> salesDetails = pileMapper.selectPilesInfo(page, params);
        page.setRecords(salesDetails);
        return page;
    }

}
