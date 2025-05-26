package com.hcp.operator.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hcp.common.core.domain.R;
import com.hcp.system.api.domain.vo.MonthTotalRspVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hcp.common.log.annotation.Log;
import com.hcp.common.log.enums.BusinessType;
import com.hcp.common.security.annotation.RequiresPermissions;
import com.hcp.system.api.domain.MenberBalance;
import com.hcp.operator.service.IMenberBalanceService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 用户余额Controller
 *
 * @author hcp
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/balance")
public class MenberBalanceController extends BaseController
{
    @Autowired
    private IMenberBalanceService menberBalanceService;

    /**
     * 查询用户余额列表
     */
    @RequiresPermissions("operator:balance:list")
    @GetMapping("/list")
    public AjaxResult list(MenberBalance menberBalance)
    {
        IPage<MenberBalance> list = menberBalanceService.selectMenberBalancePage(menberBalance);
        return AjaxResult.success(list);
    }

    /**
     * 导出用户余额列表
     */
    @RequiresPermissions("operator:balance:export")
    @Log(title = "用户余额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MenberBalance menberBalance)
    {
        List<MenberBalance> list = menberBalanceService.selectMenberBalanceList(menberBalance);
        ExcelUtil<MenberBalance> util = new ExcelUtil<MenberBalance>(MenberBalance.class);
        util.exportExcel(response, list, "用户余额数据");
    }

    /**
     * 获取用户余额详细信息
     */
    @RequiresPermissions("operator:balance:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(menberBalanceService.selectMenberBalanceById(id));
    }

    /**
     * 新增用户余额
     */
    @RequiresPermissions("operator:balance:add")
    @Log(title = "用户余额", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MenberBalance menberBalance)
    {
        return toAjax(menberBalanceService.insertMenberBalance(menberBalance));
    }

    /**
     * 修改用户余额
     */
    @RequiresPermissions("operator:balance:edit")
    @Log(title = "用户余额", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MenberBalance menberBalance)
    {
        return toAjax(menberBalanceService.updateMenberBalance(menberBalance));
    }

    /**
     * 删除用户余额
     */
    @RequiresPermissions("operator:balance:remove")
    @Log(title = "用户余额", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(menberBalanceService.deleteMenberBalanceByIds(ids));
    }

    @ApiOperation("更新用户余额")
    @PostMapping("updateMemberBalance")
    public AjaxResult updateMemberBalance(@RequestBody MenberBalance menberBalance)
    {
        return toAjax(menberBalanceService.updateMenberBalance(menberBalance));
    }
    @ApiOperation("根据用户id获取用户余额")
    @GetMapping("/info/{userId}")
    public R<MenberBalance> getMenberBalanceByUserId(@PathVariable("userId") Long userId)
    {
        MenberBalance menberBalance = menberBalanceService.getMenberBalanceByUserId(userId);
        return R.ok(menberBalance);
    }

    @PostMapping("/insertMemberBalance")
    public AjaxResult insertMenberBalance(@RequestBody MenberBalance menberBalance)
    {
        return toAjax(menberBalanceService.insertMenberBalance(menberBalance));
    }

    @GetMapping("/queryMonthTotalByUserId")
    @ApiOperation( "根据openId查看用户当月数据")
    public R<MonthTotalRspVO> queryMonthTotalByUserId(@RequestParam("userId") Long userId){

        MonthTotalRspVO vo = menberBalanceService.queryMonthTotalByUserId(userId);
        return R.ok(vo);
    }

}
