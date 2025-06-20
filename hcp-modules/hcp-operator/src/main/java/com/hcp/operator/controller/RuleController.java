package com.hcp.operator.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.hcp.common.security.utils.SecurityUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hcp.common.log.annotation.Log;
import com.hcp.common.log.enums.BusinessType;
import com.hcp.common.security.annotation.RequiresPermissions;
import com.hcp.operator.domain.Rule;
import com.hcp.operator.service.IRuleService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 收费规则Controller
 *
 * @author hcp
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/rule")
public class RuleController extends BaseController
{
    @Autowired
    private IRuleService ruleService;

    /**
     * 查询收费规则列表
     */
    @RequiresPermissions("operator:rule:list")
    @GetMapping("/list")
    public AjaxResult list(Rule rule)
    {
        IPage<Rule> list = ruleService.selectRulePage(rule);
        return AjaxResult.success(list);
    }

    /**
     * 导出收费规则列表
     */
    @RequiresPermissions("operator:rule:export")
    @Log(title = "收费规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Rule rule)
    {
        List<Rule> list = ruleService.selectRuleList(rule);
        ExcelUtil<Rule> util = new ExcelUtil<Rule>(Rule.class);
        util.exportExcel(response, list, "收费规则数据");
    }

    /**
     * 获取收费规则详细信息
     */
    @RequiresPermissions("operator:rule:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ruleService.selectRuleById(id));
    }

    /**
     * 新增收费规则
     */
    @RequiresPermissions("operator:rule:add")
    @Log(title = "收费规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Rule rule)
    {
        return ruleService.addRule4Four(rule);
    }

    /**
     * 修改收费规则
     */
    @RequiresPermissions("operator:rule:edit")
    @Log(title = "收费规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Rule rule)
    {
        return toAjax(ruleService.updateRule(rule));
    }

    /**
     * 删除收费规则
     */
    @RequiresPermissions("operator:rule:remove")
    @Log(title = "收费规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ruleService.deleteRuleByIds(ids));
    }

    @RequiresPermissions("operator:rule:list")
    @Log(title = "规则价格列表", businessType = BusinessType.DELETE)
    @GetMapping("/getRulePriceList")
    public AjaxResult getRulePriceList(@RequestParam("ruleId") @ApiParam("规则ID") int ruleId){
        List<Map<String,Object>> rulePriceList = ruleService.getRulePriceListById(ruleId);
        return  AjaxResult.success(rulePriceList);
    }


    @RequiresPermissions("operator:rule:list")
    @Log(title = "运营端-复制规则", businessType = BusinessType.DELETE)
    @GetMapping("/copyRule")
    public AjaxResult copyRule(@ApiParam("被复制的规则ID")@RequestParam("id") Long id,
                               @ApiParam("新的规则名称")@RequestParam("changeName") String changeName,
                               @ApiParam("代理商ID")@RequestParam(value = "userId",required = false) Long userId){
        Rule rule =new Rule();
        rule.setId(id);
        rule.setChangeName(changeName);
        // 如果代理商ID为空，取值当前登录运营人员id
        if(null==userId){
            userId = SecurityUtils.getUserId();
        }
        /*设置当前操作人员*/
        rule.setUserId(userId);
        /*添加规则*/
        return ruleService.copyRule(rule);

    }

    @Log(title = "删除规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteRule")
    public AjaxResult deleteRule(@ApiParam("规则ID") @RequestParam("id")int id){
        return ruleService.deleteRule(id);
    }


}
