package com.hcp.operator.service;

import java.util.List;
import com.hcp.system.api.domain.Member;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 用户Service接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface IMemberService
{
    /**
     * 查询用户
     *
     * @param memberId 用户主键
     * @return 用户
     */
    Member selectMemberByMemberId(Long memberId);

    /**
     * 查询用户列表-分页
     *
     * @param member 用户
     * @return 用户集合
     */
    IPage<Member> selectMemberPage(Member member);
    /**
     * 查询用户列表
     *
     * @param member 用户
     * @return 用户集合
     */
    List<Member> selectMemberList(Member member);

    /**
     * 新增用户
     *
     * @param member 用户
     * @return 结果
     */
    int insertMember(Member member);

    /**
     * 修改用户
     *
     * @param member 用户
     * @return 结果
     */
    int updateMember(Member member);

    /**
     * 批量删除用户
     *
     * @param memberIds 需要删除的用户主键集合
     * @return 结果
     */
    int deleteMemberByMemberIds(Long[] memberIds);

    /**
     * 删除用户信息
     *
     * @param memberId 用户主键
     * @return 结果
     */
    int deleteMemberByMemberId(Long memberId);

    Member getMemberByMemberId(Long memberId);

    Member getMemberByOpenId(String openId);


}
