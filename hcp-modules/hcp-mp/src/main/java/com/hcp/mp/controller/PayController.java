package com.hcp.mp.controller;

import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.system.api.RemoteMemberBalanceService;
import com.hcp.system.api.domain.MenberBalance;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Resource
    private RemoteMemberBalanceService remoteMemberBalanceService;

    @ApiOperation("充值接口")
    @GetMapping("/xcxRecharge")
    public AjaxResult xcxRecharge(@RequestParam("amount") String amount,
                                  @RequestParam("userId") Long userId){

//        Long userId = SecurityUtils.getUserId();
        MenberBalance balance = remoteMemberBalanceService.getMenberBalanceByUserId(userId).getData();
        balance.setAmount(new BigDecimal(amount).add(balance.getAmount()));
        AjaxResult ajaxResult = remoteMemberBalanceService.updateMemberBalance(balance);
        return ajaxResult;
    }


}
