package com.hcp.operator.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hcp.system.api.domain.Member;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 用户Mapper接口
 *
 * @author hcp
 * @date 2024-08-06
 */
public interface MemberMapper extends BaseMapperX<Member>
{

    /**
     * 查询用户列表-分页
     *
     * @param member 用户
     * @return 用户集合
     */
    IPage<Member> selectMemberList(Page page,@Param("query") Member member);
    /**
     * 查询用户列表
     *
     * @param member 用户
     * @return 用户集合
     */
    List<Member> selectMemberList(@Param("query") Member member);

    /**
     * 批量删除用户
     *
     * @param memberIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteMemberByMemberIds(Long[] memberIds);

    //根据id获取小程序用户
    @InterceptorIgnore(tenantLine = "1")
    Member selectByMemberId(@Param("memberId")Long memberId);

    //根据openId获取小程序用户
    @InterceptorIgnore(tenantLine = "1")
    Member selectByOpenId(@Param("openId")String OpenId);
}
