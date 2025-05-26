package com.hcp.system.api;

import com.hcp.common.core.constant.ServiceNameConstants;
import com.hcp.common.core.domain.R;
import com.hcp.system.api.domain.Member;
import com.hcp.system.api.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户服务
 *
 * @author vctgo
 */
@FeignClient(contextId = "remoteMemberService", value = ServiceNameConstants.OPERATOR_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteMemberService
{
    /**
     * 通过id获取小程序会员信息
     *
     * @param id 用户名
     * @return 结果
     */
    @GetMapping("/member/info/{id}")
    R<Member> getMemberInfo(@PathVariable("id") Long id);

    @GetMapping("/member/memberInfo/{openId}")
    R<Member> getMemberInfoByOpenId(@PathVariable("openId") String openId);

    /**
     * 注册用户信息
     * @return 结果
     */
    @PostMapping("/member/info/register")
    R<Member> addMemberInfo(@RequestBody Member member);


    @PostMapping("/member/info/update")
    R<Integer> updateMemberById(@RequestBody Member member);
}
