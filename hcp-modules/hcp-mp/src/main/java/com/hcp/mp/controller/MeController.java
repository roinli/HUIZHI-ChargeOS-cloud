package com.hcp.mp.controller;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hcp.common.core.domain.R;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.mp.domain.CreditDetail;
import com.hcp.mp.domain.UserCredit;
import com.hcp.mp.service.CreditDetailService;
import com.hcp.mp.service.UserCreditService;
import com.hcp.system.api.RemoteMemberBalanceService;
import com.hcp.system.api.RemoteMemberService;
import com.hcp.system.api.domain.Member;
import com.hcp.system.api.domain.MenberBalance;
import com.hcp.system.api.domain.vo.MonthTotalRspVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/me")
@Slf4j
public class MeController extends BaseController {
    @Autowired
    private RemoteMemberService remoteMemberService;
//    @Autowired
//    private RedisTemplate redisTemplate;
    @Autowired
    private RemoteMemberBalanceService remoteMemberBalanceService;
    @Autowired
    private UserCreditService userCreditService;
    @Autowired
    private CreditDetailService creditDetailService;


    @ApiOperation("getMemberInfoByOpenId")
    @GetMapping("/getMemberInfoByOpenId")
    public R<Member> getMemberInfoByOpenId(@RequestParam("openId") String openId) {
        Member user = remoteMemberService.getMemberInfoByOpenId(openId).getData();
        return R.ok(user);
    }

    /**
     * 获取账户余额
     * @return
     */
    @GetMapping("/getMemberBalanceByUserId")
    public R<MenberBalance> getMemberBalance(@RequestParam("userId") Long userId) {
//        Long userId = SecurityUtils.getUserId();
        MenberBalance menberBalance = remoteMemberBalanceService.getMenberBalanceByUserId(userId).getData();

        return R.ok(menberBalance);
    }



    @GetMapping("/getUserCredit")
    @InterceptorIgnore(tenantLine = "1")
    public R<UserCredit> getUserCredit(@RequestParam("userId") Long userId) {
//        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<UserCredit> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserCredit::getMemberId,userId);
        UserCredit userCredit = userCreditService.getOne(queryWrapper);
        return R.ok(userCredit);
    }


    /**
     * 积分明细
     * @return
     */
    @GetMapping("/creditDetail")
    public R<List<CreditDetail>> creditDetail(@RequestParam("userId") Long userId) {
//        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<CreditDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CreditDetail::getMemberId,userId);
        List<CreditDetail> list = creditDetailService.list(queryWrapper);
        return R.ok(list);
    }

    @GetMapping("/queryMonthTotalByUserId")
    @ApiOperation( "根据userId查看用户当月数据")
    public R<MonthTotalRspVO> queryMonthTotalByUserId(@RequestParam("userId") Long userId){

        MonthTotalRspVO vo = remoteMemberBalanceService.queryMonthTotalByUserId(userId).getData();
        return R.ok(vo);
    }

}
