package com.jingli.admin.controller;

import com.jingli.admin.commons.BaseController;
import com.jingli.admin.commons.Response;
import com.jingli.admin.model.PlotPile;
import com.jingli.admin.service.PileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @描述 首页地图控制器
 * @创建人 Zfenor
 */
@Controller
@RequestMapping("index")
public class MapController extends BaseController {
    @Autowired
    private PileService pileService;


    @GetMapping("")
    public String index() {
        return "map/index.html";
    }


    /**
     * @描述 获取小区充电桩数据
     * @参数 []
     * @返回值 com.jingli.commons.Response<java.util.List < com.jingli.modules.sys.map.model.PlotPile>>
     * @创建人 yangbin
     */
    @GetMapping("getPlotPile")
    @ResponseBody
    public Response<List<PlotPile>> getPlotPile() {
        List<PlotPile> plotPiles = pileService.getPlotPile();
        return Response.ok(plotPiles);
    }

}
