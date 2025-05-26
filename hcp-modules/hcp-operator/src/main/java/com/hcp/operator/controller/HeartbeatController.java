package com.hcp.operator.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hcp.common.log.annotation.Log;
import com.hcp.common.log.enums.BusinessType;
import com.hcp.common.security.annotation.RequiresPermissions;
import com.hcp.system.api.domain.Heartbeat;
import com.hcp.operator.service.IHeartbeatService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 充电桩心跳数据Controller
 *
 * @author hcp
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController extends BaseController
{
    @Autowired
    private IHeartbeatService heartbeatService;

    /**
     * 查询充电桩心跳数据列表
     */
    @RequiresPermissions("operator:heartbeat:list")
    @GetMapping("/list")
    public AjaxResult list(Heartbeat heartbeat)
    {
        IPage<Heartbeat> list = heartbeatService.selectHeartbeatPage(heartbeat);
        return AjaxResult.success(list);
    }

    /**
     * 导出充电桩心跳数据列表
     */
    @RequiresPermissions("operator:heartbeat:export")
    @Log(title = "充电桩心跳数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Heartbeat heartbeat)
    {
        List<Heartbeat> list = heartbeatService.selectHeartbeatList(heartbeat);
        ExcelUtil<Heartbeat> util = new ExcelUtil<Heartbeat>(Heartbeat.class);
        util.exportExcel(response, list, "充电桩心跳数据数据");
    }

    /**
     * 获取充电桩心跳数据详细信息
     */
    @RequiresPermissions("operator:heartbeat:query")
    @GetMapping(value = "/{pileId}")
    public AjaxResult getInfo(@PathVariable("pileId") String pileId)
    {
        return success(heartbeatService.selectHeartbeatByPileId(pileId));
    }

    /**
     * 新增充电桩心跳数据
     */
    @RequiresPermissions("operator:heartbeat:add")
    @Log(title = "充电桩心跳数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Heartbeat heartbeat)
    {
        return toAjax(heartbeatService.insertHeartbeat(heartbeat));
    }

    /**
     * 修改充电桩心跳数据
     */
    @RequiresPermissions("operator:heartbeat:edit")
    @Log(title = "充电桩心跳数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Heartbeat heartbeat)
    {
        return toAjax(heartbeatService.updateHeartbeat(heartbeat));
    }

    /**
     * 删除充电桩心跳数据
     */
    @RequiresPermissions("operator:heartbeat:remove")
    @Log(title = "充电桩心跳数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pileIds}")
    public AjaxResult remove(@PathVariable String[] pileIds)
    {
        return toAjax(heartbeatService.deleteHeartbeatByPileIds(pileIds));
    }
}
