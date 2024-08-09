
package com.jingli.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.commons.Response;
import com.jingli.admin.model.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * @ClassName User
 * @Description:
 * @Author yangbin
 * @Version V1.0
 **/
public interface UserService {


    /**
     * 系统用户登录操作
     *
     * @param form    登录表单
     * @param session session类型
     * @param type    类型
     * @return 登录结果
     */
    Response signIn(LoginForm form, HttpSession session, Integer type);

    /**
     * 通过用户账号查用户
     *
     * @param userNm 用户账号
     * @return 用户信息
     */
    User getUserById(String userNm);

    List<OperatorSelect> getUserScope(String userNm);

    IPage<UserResult> getUserList(Map<String, Object> params, Page<UserResult> page);
}