package com.hcp.system.api.factory;

import com.hcp.common.core.domain.R;
import com.hcp.system.api.RemoteMemberService;
import com.hcp.system.api.RemoteUserService;
import com.hcp.system.api.domain.Member;
import com.hcp.system.api.domain.SysUser;
import com.hcp.system.api.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author vctgo
 */
@Component
public class RemoteMemberFallbackFactory implements FallbackFactory<RemoteMemberService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteMemberFallbackFactory.class);

    @Override
    public RemoteMemberService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteMemberService()
        {

            @Override
            public R<Member> getMemberInfo(Long id) {
                return R.fail("获取会员信息是失败:" + throwable.getMessage());
            }

            @Override
            public R<Member> getMemberInfoByOpenId(String openId) {
                return R.fail("获取OpenId会员信息是失败:" + throwable.getMessage());
            }

            @Override
            public R<Member> addMemberInfo(Member member) {
                return R.fail("添加用户失败:" + throwable.getMessage());
            }

            @Override
            public R<Integer> updateMemberById(Member member) {
                return R.fail("更新用户信息失败:" + throwable.getMessage());
            }
        };
    }
}
