package com.hcp.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import com.hcp.system.api.domain.SysLogininfor;
import org.apache.ibatis.annotations.Param;

/**
 * 系统访问日志情况信息 数据层
 *
 * @author vctgo
 */
 public interface SysLogininforMapper extends BaseMapperX<SysLogininfor>
{
    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
     int insertLogininfor(SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合-分页
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    IPage<SysLogininfor> selectLogininforList(Page page, @Param("query") SysLogininfor logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
     List<SysLogininfor> selectLogininforList( @Param("query") SysLogininfor logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
     int deleteLogininforByIds(Long[] infoIds);

    /**
     * 清空系统登录日志
     *
     * @return 结果
     */
     int cleanLogininfor();

    /**
     * 查询访问次数
     *
     * @return 结果
     */
    Object countLogininfo();

    /**
     * 查询租户数量
     *
     * @return 结果
     */
    @InterceptorIgnore(tenantLine = "1")
    Object countTenant();

    /**
     * 查询注册用户
     *
     * @return 结果
     */
    @InterceptorIgnore(tenantLine = "1")
    Object countRegister();
}
