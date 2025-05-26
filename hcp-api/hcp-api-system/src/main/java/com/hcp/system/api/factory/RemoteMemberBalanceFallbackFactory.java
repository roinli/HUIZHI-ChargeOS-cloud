package com.hcp.system.api.factory;

import com.hcp.common.core.domain.R;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.system.api.RemoteMemberBalanceService;
import com.hcp.system.api.domain.MenberBalance;
import com.hcp.system.api.domain.vo.MonthTotalRspVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author vctgo
 */
@Component
@Slf4j
public class RemoteMemberBalanceFallbackFactory implements FallbackFactory<RemoteMemberBalanceService>
{

    @Override
    public RemoteMemberBalanceService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteMemberBalanceService()
        {
            @Override
            public R<MenberBalance> getMenberBalanceByUserId(Long id) {
                return R.fail("获取余额失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult updateMemberBalance(MenberBalance menberBalance) {
                return AjaxResult.error("更新余额失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult insertMemberBalance(MenberBalance menberBalance) {
                return AjaxResult.error("新增余额失败:" + throwable.getMessage());
            }

            @Override
            public R<MonthTotalRspVO> queryMonthTotalByUserId(Long userId) {
                return null;
            }


        };

    }
}
