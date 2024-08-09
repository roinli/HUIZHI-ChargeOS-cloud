package com.jingli.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.mapper.PlotMapper;
import com.jingli.admin.model.PlotResult;
import com.jingli.admin.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @描述 小区管理接口实现类
 * @创建人 yangbin
 */
@Service
public class PlotServiceImpl implements PlotService {

    @Autowired
    private PlotMapper plotMapper;
    /**
     * @描述 获取小区列表数据
     * @参数 [params查询参数, page]
     * @返回值 com.baomidou.mybatisplus.core.metadata.IPage<com.jingli.modules.sys.plot.model.PlotResult>
     * @创建人 yangbin
     */
    @Override
    public IPage<PlotResult> getPlotList(Map<String, Object> params, Page<PlotResult> page) {
        List<PlotResult> plotResultList = plotMapper.getPlotList(params, page);
        page.setRecords(plotResultList);
        return page;
    }
}
