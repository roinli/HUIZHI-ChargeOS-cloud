package com.hcp.system.api;

import com.hcp.common.core.constant.ServiceNameConstants;
import com.hcp.common.core.domain.R;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.system.api.domain.MenberBalance;
import com.hcp.system.api.domain.vo.MonthTotalRspVO;
import com.hcp.system.api.factory.RemoteMemberBalanceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 用户服务
 *
 * @author vctgo
 */
@FeignClient(contextId = "remoteMemberBalanceService", value = ServiceNameConstants.OPERATOR_SERVICE)
public interface RemoteMemberBalanceService
{
    /**
     * 通过id获取用户余额信息
     *
     * @param userId 用户名
     * @return 结果
     */
    @GetMapping("/balance/info/{userId}")
    R<MenberBalance> getMenberBalanceByUserId(@PathVariable("userId") Long userId);


    @PostMapping("/balance/updateMemberBalance")
    AjaxResult updateMemberBalance(@RequestBody MenberBalance menberBalance);

    @GetMapping("/balance/insertMemberBalance")
    AjaxResult insertMemberBalance(@RequestBody MenberBalance menberBalance);

    @GetMapping("/balance/queryMonthTotalByUserId")
    R<MonthTotalRspVO> queryMonthTotalByUserId(@RequestParam("userId") Long userId);

}
