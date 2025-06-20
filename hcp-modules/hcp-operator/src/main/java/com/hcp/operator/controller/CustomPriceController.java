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
import com.hcp.operator.domain.CustomPrice;
import com.hcp.operator.service.ICustomPriceService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 价格Controller
 *
 * @author hcp
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/price")
public class CustomPriceController extends BaseController
{
    @Autowired
    private ICustomPriceService customPriceService;

    /**
     * 查询价格列表
     */
    @RequiresPermissions("operator:price:list")
    @GetMapping("/list")
    public AjaxResult list(CustomPrice customPrice)
    {
        IPage<CustomPrice> list = customPriceService.selectCustomPricePage(customPrice);
        return AjaxResult.success(list);
    }

    /**
     * 导出价格列表
     */
    @RequiresPermissions("operator:price:export")
    @Log(title = "价格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomPrice customPrice)
    {
        List<CustomPrice> list = customPriceService.selectCustomPriceList(customPrice);
        ExcelUtil<CustomPrice> util = new ExcelUtil<CustomPrice>(CustomPrice.class);
        util.exportExcel(response, list, "价格数据");
    }

    /**
     * 获取价格详细信息
     */
    @RequiresPermissions("operator:price:query")
    @GetMapping(value = "/{priceId}")
    public AjaxResult getInfo(@PathVariable("priceId") Long priceId)
    {
        return success(customPriceService.selectCustomPriceByPriceId(priceId));
    }

    /**
     * 新增价格
     */
    @RequiresPermissions("operator:price:add")
    @Log(title = "价格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomPrice customPrice)
    {
        return toAjax(customPriceService.insertCustomPrice(customPrice));
    }

    /**
     * 修改价格
     */
    @RequiresPermissions("operator:price:edit")
    @Log(title = "价格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomPrice customPrice)
    {
        return toAjax(customPriceService.updateCustomPrice(customPrice));
    }

    /**
     * 删除价格
     */
    @RequiresPermissions("operator:price:remove")
    @Log(title = "价格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{priceIds}")
    public AjaxResult remove(@PathVariable Long[] priceIds)
    {
        return toAjax(customPriceService.deleteCustomPriceByPriceIds(priceIds));
    }
}
