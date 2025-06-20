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
import com.hcp.operator.domain.HlhtOperatorInfo;
import com.hcp.operator.service.IHlhtOperatorInfoService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 运营商信息Controller
 *
 * @author hcp
 * @date 2024-08-11
 */
@RestController
@RequestMapping("/operatorInfo")
public class HlhtOperatorInfoController extends BaseController
{
    @Autowired
    private IHlhtOperatorInfoService hlhtOperatorInfoService;

    /**
     * 查询运营商信息列表
     */
    @RequiresPermissions("operator:operatorInfo:list")
    @GetMapping("/list")
    public AjaxResult list(HlhtOperatorInfo hlhtOperatorInfo)
    {
        IPage<HlhtOperatorInfo> list = hlhtOperatorInfoService.selectHlhtOperatorInfoPage(hlhtOperatorInfo);
        return AjaxResult.success(list);
    }

    /**
     * 导出运营商信息列表
     */
    @RequiresPermissions("operator:operatorInfo:export")
    @Log(title = "运营商信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HlhtOperatorInfo hlhtOperatorInfo)
    {
        List<HlhtOperatorInfo> list = hlhtOperatorInfoService.selectHlhtOperatorInfoList(hlhtOperatorInfo);
        ExcelUtil<HlhtOperatorInfo> util = new ExcelUtil<HlhtOperatorInfo>(HlhtOperatorInfo.class);
        util.exportExcel(response, list, "运营商信息数据");
    }

    /**
     * 获取运营商信息详细信息
     */
    @RequiresPermissions("operator:operatorInfo:query")
    @GetMapping(value = "/{operatorId}")
    public AjaxResult getInfo(@PathVariable("operatorId") String operatorId)
    {
        return success(hlhtOperatorInfoService.selectHlhtOperatorInfoByOperatorId(operatorId));
    }

    /**
     * 新增运营商信息
     */
    @RequiresPermissions("operator:operatorInfo:add")
    @Log(title = "运营商信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HlhtOperatorInfo hlhtOperatorInfo)
    {
        return toAjax(hlhtOperatorInfoService.insertHlhtOperatorInfo(hlhtOperatorInfo));
    }

    /**
     * 修改运营商信息
     */
    @RequiresPermissions("operator:operatorInfo:edit")
    @Log(title = "运营商信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HlhtOperatorInfo hlhtOperatorInfo)
    {
        return toAjax(hlhtOperatorInfoService.updateHlhtOperatorInfo(hlhtOperatorInfo));
    }

    /**
     * 删除运营商信息
     */
    @RequiresPermissions("operator:operatorInfo:remove")
    @Log(title = "运营商信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{operatorIds}")
    public AjaxResult remove(@PathVariable String[] operatorIds)
    {
        return toAjax(hlhtOperatorInfoService.deleteHlhtOperatorInfoByOperatorIds(operatorIds));
    }
}
