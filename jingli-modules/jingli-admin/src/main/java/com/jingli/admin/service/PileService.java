package com.jingli.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.model.PlotPile;
import com.jingli.admin.model.SysPileResult;

import java.util.List;
import java.util.Map;

public interface PileService {

    /**
     * 获取充电桩分页列表
     *
     * @param params 参数
     * @param page   分页信息
     * @return 分页数据
     */
    IPage<SysPileResult> getPage(Map<String, String> params, Page<SysPileResult> page);
    /**
     * 获取充电站分页列表
     *
     * @return 分页数据
     */
    List<PlotPile> getPlotPile();
}
