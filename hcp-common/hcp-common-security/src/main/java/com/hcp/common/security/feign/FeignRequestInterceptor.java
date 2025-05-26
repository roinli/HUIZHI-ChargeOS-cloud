package com.hcp.common.security.feign;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;


import com.hcp.common.core.context.SecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.hcp.common.core.constant.SecurityConstants;
import com.hcp.common.core.utils.ServletUtils;
import com.hcp.common.core.utils.StringUtils;
import com.hcp.common.core.utils.ip.IpUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * feign 请求拦截器
 *
 * @author vctgo
 */
@Slf4j
@Component
public class FeignRequestInterceptor implements RequestInterceptor
{

    @Override
    public void apply(RequestTemplate requestTemplate)
    {
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();

        if (StringUtils.isNotNull(httpServletRequest))
        {
            Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
            // 传递用户信息请求头，防止丢失
            log.debug(headers.toString());
            String userId = headers.get(SecurityConstants.DETAILS_USER_ID);
            if (StringUtils.isNotEmpty(userId))
            {
                requestTemplate.header(SecurityConstants.DETAILS_USER_ID, userId);
            }
            String userKey = headers.get(SecurityConstants.USER_KEY);
            if (StringUtils.isNotEmpty(userKey))
            {
                requestTemplate.header(SecurityConstants.USER_KEY, userKey);
            }
            String userName = headers.get(SecurityConstants.DETAILS_USERNAME);
            if (StringUtils.isNotEmpty(userName))
            {
                requestTemplate.header(SecurityConstants.DETAILS_USERNAME, userName);
            }
            String authentication = headers.get(SecurityConstants.AUTHORIZATION_HEADER);
            if (StringUtils.isNotEmpty(authentication))
            {
                requestTemplate.header(SecurityConstants.AUTHORIZATION_HEADER, authentication);
            }
            Map<String,Object> remoteheader = SecurityContextHolder.get(SecurityConstants.REMOTE_HEADER, HashMap.class);
            if (remoteheader!=null && remoteheader.size()>0 && !remoteheader.isEmpty())
            {
                for (String key : remoteheader.keySet()) {
                    requestTemplate.removeHeader(key);
                    requestTemplate.header(key, (String) remoteheader.get(key));
                }
            }
            String tenantid = headers.get(SecurityConstants.DETAILS_TENANT_ID);
            if (StringUtils.isNotEmpty(tenantid))
            {
                requestTemplate.header(SecurityConstants.DETAILS_TENANT_ID, tenantid);
            }

            String deptid = headers.get(SecurityConstants.DETAILS_DEPT_ID);
            if (StringUtils.isNotEmpty(deptid))
            {
                requestTemplate.header(SecurityConstants.DETAILS_DEPT_ID, deptid);
            }

            // 配置客户端IP
            requestTemplate.header("X-Forwarded-For", IpUtils.getIpAddr());
        }
    }
}
