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
import com.hcp.operator.domain.City;
import com.hcp.operator.service.ICityService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 省市管理Controller
 *
 * @author hcp
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/city")
public class CityController extends BaseController
{
    @Autowired
    private ICityService cityService;

    /**
     * 查询省市管理列表
     */
    @RequiresPermissions("operator:city:list")
    @GetMapping("/list")
    public AjaxResult list(City city)
    {
        IPage<City> list = cityService.selectCityPage(city);
        return AjaxResult.success(list);
    }

    /**
     * 导出省市管理列表
     */
    @RequiresPermissions("operator:city:export")
    @Log(title = "省市管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, City city)
    {
        List<City> list = cityService.selectCityList(city);
        ExcelUtil<City> util = new ExcelUtil<City>(City.class);
        util.exportExcel(response, list, "省市管理数据");
    }

    /**
     * 获取省市管理详细信息
     */
    @RequiresPermissions("operator:city:query")
    @GetMapping(value = "/{Id}")
    public AjaxResult getInfo(@PathVariable("Id") Long Id)
    {
        return success(cityService.selectCityById(Id));
    }

    /**
     * 新增省市管理
     */
    @RequiresPermissions("operator:city:add")
    @Log(title = "省市管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody City city)
    {
        return toAjax(cityService.insertCity(city));
    }

    /**
     * 修改省市管理
     */
    @RequiresPermissions("operator:city:edit")
    @Log(title = "省市管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody City city)
    {
        return toAjax(cityService.updateCity(city));
    }

    /**
     * 删除省市管理
     */
    @RequiresPermissions("operator:city:remove")
    @Log(title = "省市管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{Ids}")
    public AjaxResult remove(@PathVariable Long[] Ids)
    {
        return toAjax(cityService.deleteCityByIds(Ids));
    }
}
