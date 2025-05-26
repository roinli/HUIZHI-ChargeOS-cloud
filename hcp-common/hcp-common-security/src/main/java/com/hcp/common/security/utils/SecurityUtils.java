package com.hcp.common.security.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.hcp.common.core.constant.SecurityConstants;
import com.hcp.common.core.constant.TokenConstants;
import com.hcp.common.core.context.SecurityContextHolder;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.core.utils.StringUtils;
import com.hcp.system.api.model.LoginUser;

/**
 * 权限获取工具类
 *
 * @author vctgo
 */
public class SecurityUtils
{

    /**
     * 获取租户ID
     */
    public static Long getTenantId()
    {
        return SecurityContextHolder.getTenantId();
    }

    /**
     * 设置租户ID
     */
    public static void setTenantId(String tenantId)
    {
         SecurityContextHolder.setTenantId(tenantId);
    }

    /**
     * 获取部门ID
     */
    public static Long getDeptId()
    {
        return SecurityContextHolder.getDeptId();
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId()
    {
        return SecurityContextHolder.getUserId();
    }

    /**
     * 获取用户名称
     */
    public static String getUsername()
    {
        return SecurityContextHolder.getUserName();
    }

    /**
     * 获取用户key
     */
    public static String getUserKey()
    {
        return SecurityContextHolder.getUserKey();
    }

    /**
     * 获取登录用户信息
     */
    public static LoginUser getLoginUser()
    {
        return SecurityContextHolder.get(SecurityConstants.LOGIN_USER, LoginUser.class);
    }

    /**
     * 获取请求token
     */
    public static String getToken()
    {
        return getToken(ServletUtils.getRequest());
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request)
    {
        // 从header获取token标识
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    /**
     * 裁剪token前缀
     */
    public static String replaceTokenPrefix(String token)
    {
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, "");
        }
        return token;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 随机生成n位字符串
     *
     * @param n 密码
     * @return 字符串
     */
    public static String randomStr(int n)
    {
        StringBuffer bufferStr = new StringBuffer();
        for(int i=0;i<n;i++)
        {
            bufferStr.append("0123456789ABCDEFGHIZKLMNOPQRSTUVWXYZ".charAt((int)(Math.random() * 36)));
        }
        return bufferStr.toString();
    }

    /**
     * 随机生成n位字符串
     *
     * @param n 密码
     * @return 字符串
     */
    public static String randomPassword(int n)
    {
        StringBuffer bufferStr = new StringBuffer();
        for(int i=0;i<n;i++)
        {
            bufferStr.append("0123456789".charAt((int)(Math.random() * 10)));
        }
        return bufferStr.toString();
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
