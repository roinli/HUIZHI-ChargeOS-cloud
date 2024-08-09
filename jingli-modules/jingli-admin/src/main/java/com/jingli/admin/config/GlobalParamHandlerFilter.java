package com.jingli.admin.config;

import com.alibaba.fastjson2.JSONObject;
import com.jingli.admin.util.IdGen;
import com.jingli.admin.util.LogUtil;
import com.jingli.admin.util.StringUtils;
import com.jingli.admin.util.http.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 入参和出参记录
 *  @author Zfenor
 */
@Slf4j
public class GlobalParamHandlerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put("-batchId", IdGen.uuid());
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);

        // 格式化入参
        Map<String, Object> dataMap = assembleBodyParam(httpRequest);
        String ip = RequestUtil.getRemoteIpAddr(httpRequest);
        String url = httpRequest.getRequestURI();

        String requestBody = (String) dataMap.get("requestBody");
        if (StringUtils.isEmpty(requestBody)) {
            JSONObject jsonObject = RequestUtil.getAllParameter(httpRequest);
            requestBody = jsonObject.toJSONString();
        }
        // 打印开始参数
        LogUtil.logRequest(url, ip, requestBody);

        request = dataMap.containsKey("requestWrapper") ? (ServletRequest) dataMap.get("requestWrapper") : request;

        // 函数继续执行
        chain.doFilter(request, responseWrapper);

        byte[] bytes = responseWrapper.getResponseData();
        response.getOutputStream().write(bytes);
        // 打印返回结果
        LogUtil.logResponse(url, new String(bytes, "UTF-8"));
        MDC.remove("-batchId");
    }


    private Map<String, Object> assembleBodyParam(HttpServletRequest request) {
        Map<String, Object> hashMap = new HashMap<>(2);
        String requestBody = "";
        String contentType = request.getContentType();
        if (StringUtils.isNotEmpty(contentType)
                && contentType.toLowerCase().contains("application/json")) {
            ServletRequest requestWrapper = new RequestWrapper(request);
            try {
                requestBody = RequestUtil.getBodyString(requestWrapper);

            } catch (Exception e) {
                log.warn("全局过滤器(GlobalParamHandlerFilter)JSON转化异常, 不影响后续执行:{}", e.getMessage());
            } finally {
                hashMap.put("requestWrapper", requestWrapper);
            }
        }
        hashMap.put("requestBody", requestBody);
        return hashMap;
    }

    @Override
    public void destroy() {
    }

}
