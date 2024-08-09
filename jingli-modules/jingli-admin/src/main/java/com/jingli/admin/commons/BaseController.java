package com.jingli.admin.commons;

import com.alibaba.fastjson2.JSONObject;
import com.jingli.admin.constant.CacheConstant;
import com.jingli.admin.constant.Constant;
import com.jingli.admin.model.User;
import com.jingli.admin.util.HttpContext;
import com.jingli.common.core.utils.ServletUtils;
import com.jingli.common.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

import static com.jingli.admin.constant.Constant.USER_IS_OPERATOR;

/**
 * 基础Controller
 *
 * @author Zfenor
 **/
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private RedisService redisService;

    public BaseController() {
    }

    protected HttpServletRequest getHttpServletRequest() {
        return HttpContext.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpContext.getResponse();
    }

    protected HttpSession getSession() {
        return Objects.requireNonNull(HttpContext.getRequest()).getSession();
    }

    protected HttpSession getSession(Boolean flag) {
        return Objects.requireNonNull(HttpContext.getRequest()).getSession(flag);
    }

    protected void setAttr(String name, Object value) {
        Objects.requireNonNull(HttpContext.getRequest()).setAttribute(name, value);
    }


    /**
     * 获取当前登录人用户名
     *
     * @return 用户名
     */
    protected String getUserNm() {
        // session中获取用户名
        String userName = (String) getSession().getAttribute(Constant.USER_SESSION_KEY);
        if (userName == null) {
            Map<String, String> headers = ServletUtils.getHeaders(Objects.requireNonNull(HttpContext.getRequest()));
            System.out.println("getToken:{}" + "tk" + headers.get("token"));
            userName = redisService.getCacheObject("tk" + headers.get("token"));
            return userName;
        }
        return userName;
    }

    /**
     * 获取session中用户信息
     *
     * @return 用户信息
     */
    protected User getUserInfo() {
        HttpSession session = getSession();

        Object attribute = session.getAttribute(Constant.USERINFO_SESSION_KEY);
        logger.info("用户session数据:{}" + session.getId(), JSONObject.toJSONString(attribute));
        if (attribute == null) {
            Map<String, String> headers = ServletUtils.getHeaders(Objects.requireNonNull(HttpContext.getRequest()));
            System.out.println("getToken:{}" + "tk" + headers.get("token"));
            String userName = redisService.getCacheObject("tk" + headers.get("token"));
            return JSONObject.parseObject(redisService.getCacheObject(CacheConstant.User.PREFIX_NAME + userName), User.class);
        }
        return (User) attribute;
    }

    /**
     * 获取用户ID
     *
     * @return 用户id
     */
    protected Integer getUserId() {
        return getUserInfo().getId();
    }

    /**
     * 获取平台类型
     *
     * @return 平台类型
     */
    protected Object getPlatForm() {
        User user = getUserInfo();
        return user.getDeviceType();
    }

    /**
     * 获取是否代理商
     *
     * @return 是否代理商
     */
    protected boolean isOperator() {
        User user = getUserInfo();
        return user.getIsOperator().equals(USER_IS_OPERATOR);
    }

}
