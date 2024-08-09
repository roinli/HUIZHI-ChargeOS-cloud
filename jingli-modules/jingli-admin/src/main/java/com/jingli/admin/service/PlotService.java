package com.jingli.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.model.PlotResult;

import java.util.Map;

/**
 * 小区管理接口
 *
 * @author yangbin
 */
public interface PlotService {
    IPage<PlotResult> getPlotList(Map<String, Object> params, Page<PlotResult> page);
}
