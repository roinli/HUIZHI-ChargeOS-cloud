package com.hcp.operator.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.log.annotation.Log;
import com.hcp.common.log.enums.BusinessType;
import com.hcp.common.security.annotation.RequiresPermissions;
import com.hcp.common.security.utils.SecurityUtils;
import com.hcp.operator.domain.ManagerTotalDataVO;
import com.hcp.operator.domain.PileTotalResult;
import com.hcp.operator.domain.QueryChargePileVo;
import com.hcp.operator.service.IChargingOrderService;
import com.hcp.operator.service.IChargingPileService;
import com.hcp.system.api.domain.SysDept;
import com.hcp.system.api.domain.SysUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/total")
public class TotalDataController {
    private final static Logger logger = LoggerFactory.getLogger(TotalDataController.class);
    @Autowired
    private IChargingOrderService chargingOrderService;
    @Resource
    private IChargingPileService chargingPileService;



    @ApiOperation("经营管理-销售统计")
    @PostMapping("/getManageTotalData")
    @ResponseBody
    public AjaxResult getManageTotalData(@RequestBody QueryChargePileVo vo){
        Map<String, Object> params = BeanUtil.beanToMap(vo);

        ManagerTotalDataVO managerTotalDataVO = chargingOrderService.getManageTotalData(vo);
        return AjaxResult.success(managerTotalDataVO);
    }

    @ApiOperation(value = "获取收益统计列表")
    @PostMapping("/getChargeTotal")
    public AjaxResult getChargeTotal(@RequestBody QueryChargePileVo vo) {
        logger.info("获取收益统计列表: {}", JSONUtil.toJsonStr(vo));

        IPage<PileTotalResult> pileList = chargingPileService.getChargeTotalPage(vo);
        return AjaxResult.success(pileList);
    }

    @GetMapping("/getTotalUserList")
    @ApiOperation("获取数据权限下用户")
    public AjaxResult getTotalUserList() {

        return AjaxResult.success();
    }

    @Log(title = "经营管理-下载统计列表", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadManageTotalData")
    public void downloadManageTotalData(HttpServletResponse response, QueryChargePileVo vo)
    {
        ManagerTotalDataVO managerTotalDataVO = chargingOrderService.getManageTotalData(vo);
        ExcelUtil<ManagerTotalDataVO> util = new ExcelUtil<ManagerTotalDataVO>(ManagerTotalDataVO.class);
        List<ManagerTotalDataVO> list = new ArrayList<>();
        list.add(managerTotalDataVO);
        util.exportExcel(response, list, "经营管理-统计数据");
    }

    @Log(title = "经营管理-下载统计列表", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadStationList")
    public void downloadStationList(HttpServletResponse response, QueryChargePileVo vo)
    {

        List<PileTotalResult> pileList = chargingPileService.getChargeTotal(vo);
        ExcelUtil<PileTotalResult> util = new ExcelUtil<PileTotalResult>(PileTotalResult.class);
        util.exportExcel(response, pileList, "经营管理-站点数据");
    }



}
