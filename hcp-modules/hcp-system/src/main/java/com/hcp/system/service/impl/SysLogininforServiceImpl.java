package com.hcp.system.service.impl;

import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.core.constant.CacheConstants;
import com.hcp.common.core.text.Convert;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.core.web.domain.AjaxResult;
import com.hcp.common.mybatisplus.constant.MybatisPageConstants;
import com.hcp.common.redis.service.RedisService;
import com.hcp.system.domain.SysLoginCount;
import com.hcp.system.mapper.SysLogininforMapper;
import com.hcp.system.service.ISysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcp.system.api.domain.SysLogininfor;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author vctgo
 */
@Service
public class SysLogininforServiceImpl implements ISysLogininforService
{

    @Autowired
    private SysLogininforMapper logininforMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public int insertLogininfor(SysLogininfor logininfor)
    {
        return logininforMapper.insertLogininfor(logininfor);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public IPage<SysLogininfor> selectLogininforPage(SysLogininfor logininfor)
    {
        Page mpPage =new Page(Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_NUM),1L)
                ,Convert.toLong(ServletUtils.getParameterToInt(MybatisPageConstants.PAGE_SIZE),10L));
        return logininforMapper.selectLogininforList(mpPage,logininfor);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor)
    {
        return logininforMapper.selectLogininforList(logininfor);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    @Override
    public int deleteLogininforByIds(Long[] infoIds)
    {
        return logininforMapper.deleteLogininforByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor()
    {
        logininforMapper.cleanLogininfor();
    }

    /**
     * 查询统计信息
     * @return
     */
    @Override
    public AjaxResult logincount() {
        SysLoginCount loginCountInfo = new SysLoginCount();
        //step1 在线人数
        Collection<String> online = redisService.keys(CacheConstants.LOGIN_TOKEN_KEY + "*");
        loginCountInfo.setOnlineCount(String.valueOf(online.size()));
        //step2 累积登陆人数 超级管理员统计所有
        loginCountInfo.setLoginCount(String.valueOf(logininforMapper.countLogininfo()));
        //step 累积租户数
        loginCountInfo.setTenantCount(String.valueOf(logininforMapper.countTenant()));
        //step4 累积注册人数
        loginCountInfo.setRegisterCount(String.valueOf(logininforMapper.countRegister()));
        return AjaxResult.success(loginCountInfo);
    }
}
