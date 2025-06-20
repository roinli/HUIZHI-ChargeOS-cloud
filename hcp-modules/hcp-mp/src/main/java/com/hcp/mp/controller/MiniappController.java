package com.hcp.mp.controller;

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

import com.hcp.common.security.annotation.RequiresPermissions;
import com.hcp.system.api.domain.Miniapp;
import com.hcp.mp.service.IMiniappService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 小程序Controller
 *
 * @author hcp
 * @date 2024-08-10
 */
@RestController
@RequestMapping("/miniapp")
public class MiniappController extends BaseController
{
    @Autowired
    private IMiniappService miniappService;

    /**
     * 查询小程序列表
     */
    @RequiresPermissions("mp:miniapp:list")
    @GetMapping("/list")
    public AjaxResult list(Miniapp miniapp)
    {
        IPage<Miniapp> list = miniappService.selectMiniappPage(miniapp);
        return AjaxResult.success(list);
    }

    /**
     * 导出小程序列表
     */
    @RequiresPermissions("mp:miniapp:export")
//    @Log(title = "小程序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Miniapp miniapp)
    {
        List<Miniapp> list = miniappService.selectMiniappList(miniapp);
        ExcelUtil<Miniapp> util = new ExcelUtil<Miniapp>(Miniapp.class);
        util.exportExcel(response, list, "小程序数据");
    }

    /**
     * 获取小程序详细信息
     */
    @RequiresPermissions("mp:miniapp:query")
    @GetMapping(value = "/{appId}")
    public AjaxResult getInfo(@PathVariable("appId") String appId)
    {
        return success(miniappService.selectMiniappByAppId(appId));
    }

    /**
     * 新增小程序
     */
    @RequiresPermissions("mp:miniapp:add")
//    @Log(title = "小程序", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Miniapp miniapp)
    {
        return toAjax(miniappService.insertMiniapp(miniapp));
    }

    /**
     * 修改小程序
     */
    @RequiresPermissions("mp:miniapp:edit")
//    @Log(title = "小程序", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Miniapp miniapp)
    {
        return toAjax(miniappService.updateMiniapp(miniapp));
    }

    /**
     * 删除小程序
     */
    @RequiresPermissions("mp:miniapp:remove")
//    @Log(title = "小程序", businessType = BusinessType.DELETE)
	@DeleteMapping("/{appIds}")
    public AjaxResult remove(@PathVariable String[] appIds)
    {
        return toAjax(miniappService.deleteMiniappByAppIds(appIds));
    }
}
