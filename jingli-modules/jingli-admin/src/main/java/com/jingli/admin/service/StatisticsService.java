package com.jingli.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.model.PilesDetail;
import com.jingli.admin.model.SalesData;
import com.jingli.admin.model.SalesDetail;

import java.util.Map;

/**
 * @描述 统计接口
 * @创建人 yangbin
 */
public interface StatisticsService {
    /**
     * 根据平台获取用户销售数据
     *
     * @param platForm 平台类型
     * @return 销售数据
     */
    Map<String, Object> getUsersAndSales(Map<String, Object> platForm);

    /**
     * 查询销售数据
     *
     * @param platForm 平台类型
     * @return 销售数据
     */
    SalesData getSalesData(Map<String, Object> platForm);

    IPage<SalesDetail> getSalesDetail(Page<SalesDetail> page, Map<String, Object> platForm);

    /**
     * 获取充电桩信息
     *
     * @param page   分页信息
     * @param params 平台和代理商id
     * @return 充电桩信息
     */
    IPage<PilesDetail> getPilesDetail(Page<PilesDetail> page, Map<String, Object> params);
}
