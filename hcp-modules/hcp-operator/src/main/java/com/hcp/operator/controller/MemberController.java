package com.hcp.operator.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.hcp.common.core.domain.R;
import com.hcp.common.security.annotation.InnerAuth;
import com.hcp.system.api.RemoteMpService;
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
import com.hcp.system.api.domain.Member;
import com.hcp.operator.service.IMemberService;
import com.hcp.common.core.web.controller.BaseController;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.core.utils.poi.ExcelUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 用户Controller
 *
 * @author hcp
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController
{
    @Autowired
    private IMemberService memberService;
    @Autowired
    private RemoteMpService remoteMpService;

    /**
     * 查询用户列表
     */
    @RequiresPermissions("operator:member:list")
    @GetMapping("/list")
    public AjaxResult list(Member member)
    {
        IPage<Member> list = memberService.selectMemberPage(member);
        return AjaxResult.success(list);
    }

    /**
     * 导出用户列表
     */
    @RequiresPermissions("operator:member:export")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Member member)
    {
        List<Member> list = memberService.selectMemberList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @RequiresPermissions("operator:member:query")
    @GetMapping(value = "/{memberId}")
    public AjaxResult getInfo(@PathVariable("memberId") Long memberId)
    {
        return success(memberService.selectMemberByMemberId(memberId));
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("operator:member:add")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Member member)
    {
        return toAjax(memberService.insertMember(member));
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("operator:member:edit")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Member member)
    {
        return toAjax(memberService.updateMember(member));
    }

    /**
     * 删除用户
     */
    @RequiresPermissions("operator:member:remove")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{memberIds}")
    public AjaxResult remove(@PathVariable Long[] memberIds)
    {
        return toAjax(memberService.deleteMemberByMemberIds(memberIds));
    }

    @GetMapping("/info/{id}")
    R<Member> getMemberInfo(@PathVariable("id") Long id){
        return R.ok(memberService.getMemberByMemberId(id));
    }
    @GetMapping("/memberInfo/{openId}")
    R<Member> getMemberInfoByOpenId(@PathVariable("openId") String openId){
        return R.ok(memberService.getMemberByOpenId(openId));
    }

    /**
     * 注册用户信息
     * @return 结果
     */
    @PostMapping("/info/register")
    R<Member> addMemberInfo(@RequestBody Member member){
        memberService.insertMember(member);
        return R.ok(member);
    }


    @PostMapping("/info/update")
    public R<Integer> updateMemberById(@RequestBody Member member){
        int i = memberService.updateMember(member);
        return R.ok(i);
    }
}
