package com.hcp.operator.service.impl;

import java.util.List;

import com.hcp.common.mybatisplus.query.LambdaQueryWrapperX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.operator.mapper.MemberMapper;
import com.hcp.system.api.domain.Member;
import com.hcp.operator.service.IMemberService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
/**
 * 用户Service业务层处理
 *
 * @author hcp
 * @date 2024-08-06
 */
@Service
public class MemberServiceImpl implements IMemberService
{
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询用户
     *
     * @param memberId 用户主键
     * @return 用户
     */
    @Override
    public Member selectMemberByMemberId(Long memberId)
    {
        return memberMapper.selectById(memberId);
    }

    /**
     * 查询用户列表-分页
     *
     * @param member 用户
     * @return 用户
     */
    @Override
    public IPage<Member> selectMemberPage(Member member)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return memberMapper.selectMemberList(mpPage,member);
    }
    /**
     * 查询用户列表
     *
     * @param member 用户
     * @return 用户
     */
    @Override
    public List<Member> selectMemberList(Member member)
    {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增用户
     *
     * @param member 用户
     * @return 结果
     */

    @Override
    public int insertMember(Member member)
    {
        return memberMapper.insert(member);
    }

    /**
     * 修改用户
     *
     * @param member 用户
     * @return 结果
     */
    @Override
    public int updateMember(Member member)
    {
        return memberMapper.updateById(member);
    }

    /**
     * 批量删除用户
     *
     * @param memberIds 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteMemberByMemberIds(Long[] memberIds)
    {
        return memberMapper.deleteMemberByMemberIds(memberIds);
    }

    /**
     * 删除用户信息
     *
     * @param memberId 用户主键
     * @return 结果
     */
    @Override
    public int deleteMemberByMemberId(Long memberId)
    {
        return memberMapper.deleteById(memberId);
    }

    @Override
    public Member getMemberByMemberId(Long memberId) {
        return memberMapper.selectByMemberId(memberId);
    }

    @Override
    public Member getMemberByOpenId(String openId) {
        return memberMapper.selectByOpenId(openId);
    }
}
