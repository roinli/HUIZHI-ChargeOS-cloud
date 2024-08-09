
package com.jingli.admin.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.commons.Response;
import com.jingli.admin.constant.CacheConstant;
import com.jingli.admin.constant.Constant;
import com.jingli.admin.constant.UserErrorEnum;
import com.jingli.admin.mapper.*;
import com.jingli.admin.model.*;
import com.jingli.admin.service.*;
import com.jingli.admin.util.StringUtils;
import com.jingli.admin.util.auth.AuthUtils;
import com.jingli.admin.util.token.JwtTokenUtil;
import com.jingli.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.jingli.admin.constant.Constant.*;

/**
 * @ClassName UserServiceImpl
 * @Description: 系统用户实现类
 * @Author yangbin
 * @Version V1.0
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {


    /**
     * token 过期时间  单位秒   2小时
     */
    private static long AUTH_EXPIRE = 60 * 60 * 2;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;
    /**
     * token 前缀
     */
    @Value("${remote.prefix}")
    private String remotePrefix;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserDatascopeMapper userDatascopeMapper;
    @Autowired
    private OperatorService operatorService;

    /**
     * 功能描述：代理商或者运营端登录
     *
     * @param form    用户登录
     * @param session session信息
     * @param type    登录类型，1代理商，0 运营端
     * @return com.jingli.commons.Response 返回结果
     * @author Guo Liangbo
     */
    @Override
    public Response signIn(LoginForm form, HttpSession session, Integer type) {
        // 检验用户名密码是否为空
        String userName = form.getUserName();
        User user = userMapper.selectById(userName);
        Short platForm = form.getPlatForm();
        if (Objects.isNull(platForm)) {
            platForm = DEVICE_TYPE_FOUR;
        }
        // 如果按用户名查不到，使用电话号码查一下
        if (null == user) {
            user = userMapper.selectByTelephone(userName);
        }
        log.info("登录查询的结果是: {}", JSONUtil.toJsonStr(user));
        if (ObjUtil.isNotEmpty(user)) {
            userName = user.getUseraccount();
            String password1 = AuthUtils.md5Hex(form.getPassword(), user.getSalt());
            if (password1.equals(user.getPassword())) {
                // 判断是否代理商，不允许代理商登录
                if (StringUtils.equals("0", user.getState())) {
                    return Response.error(-1, "用户已禁用，不允许登录");
                }
                // 登录逻辑处理，控制四轮车用户只能登录四轮车平台，二轮车用户只能登录二轮车平台
                Short deviceType = user.getDeviceType();
                if (!deviceType.equals(platForm)) {
                    String errMessage = platForm.equals(DEVICE_TYPE_TWO) ? "二轮车平台" : "四轮车平台";
                    return Response.error(-1, "该用户不能登录" + errMessage);
                }
                // 登录成功,将Token最为键，用户信息作为值存入Redis
                String accessToken = JwtTokenUtil.generateToken(userName);
                Date expire = JwtTokenUtil.getClaimFromToken(accessToken).getExpiration();
                String key = remotePrefix + accessToken;
                // 登录成功,将Token最为键，用户信息作为值存入Redis
                redisService.deleteObject(key);
                redisService.setCacheObject(key, userName, AUTH_EXPIRE, TimeUnit.SECONDS);
                redisService.setCacheObject(PLAT_FORM + accessToken, deviceType + "", AUTH_EXPIRE, TimeUnit.SECONDS);
                redisService.setCacheObject(CacheConstant.User.PREFIX_NAME + userName, JSON.toJSONString(user), AUTH_EXPIRE, TimeUnit.SECONDS);
                Map<String, Object> result = new HashMap<>(8);
                result.put("token", accessToken);
                result.put("userName", userName);
                // 类型转换
                user.setPassword("");
                result.put("userInfo", user);
                // 过期时间
                result.put("expire", expire);
                // 将平台类型放入session中
                session.setAttribute(Constant.USERINFO_SESSION_KEY, getUserById(userName));
                log.info("session中的值:{}", JSONObject.toJSONString(session.getAttribute(Constant.USERINFO_SESSION_KEY)));
                return Response.ok(result);
            } else {
                log.info("用户账号 :{} 输入密码错误,", userName);
                return Response.error(UserErrorEnum.SIGN_IN_INPUT_FAIL);
            }


        }
        return Response.error(UserErrorEnum.SIGN_IN_INPUT_NULL);
    }

    /**
     * @描述 获取用户信息
     * @参数 [userNm 用户名]
     * @返回值 com.jingli.modules.sys.user.model.User
     * @创建人 yangbin
     */
    @Override
    public User getUserById(String userNm) {
        String s = redisService.getCacheObject(CacheConstant.User.PREFIX_NAME + userNm);
        if (StringUtils.isNotEmpty(s)) {
            return JSON.parseObject(s, User.class);
        }
        /*通过用户账号查询用户*/
        User user = userMapper.selectById(userNm);
        /*设置用户角色数据*/
        List<RolePO> roles = roleService.getRoleByUserId(user.getId());
        user.setRoles(roles);
        Integer[] scopeArr = getDataScopeArr(userNm);
        user.setDataScope(scopeArr);
        return user;
    }

    private Integer[] getDataScopeArr(String usrNm) {
        List<OperatorSelect> operatorSelects = getUserScope(usrNm);
        if (operatorSelects == null) {
            return null;
        }
        Integer[] dataScope = new Integer[operatorSelects.size()];
        int i = 0;
        for (OperatorSelect o : operatorSelects) {
            dataScope[i] = o.getOperatorId();
        }
        return dataScope;
    }


    /**
     * @描述 获取用户数据权限
     * @参数 [userId 当前登录用户的id]
     * @返回值 java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @创建人 yangbin
     */
    @Override
    public List<OperatorSelect> getUserScope(String userNm) {
        User user = userMapper.selectById(userNm);
        /*通过角色获取用户可看代理商list*/
        List<OperatorSelect> scopeList = operatorService.getOperatorSelect();
        /*如果数据权限为全部 显示所有代理商*/
        Integer dataPermissions = user.getDataPermissions();
        Integer isOperator = user.getIsOperator();
        // 如果用户是代理商，需要根据dataPermissions获取权限
        if (isOperator.equals(LOGIN_TYPE_OPERATOR)) {
            if (dataPermissions == 2) {
                OperatorSelect operatorSelect = new OperatorSelect();
                operatorSelect.setOperatorId(user.getId());
                operatorSelect.setOperatorNm(user.getRealName());
                return Arrays.asList(operatorSelect);
            } else if (dataPermissions == 1) {
                return scopeList;
            }
            // 不是代理商，就赋予全部代理商权限
        } else {
            return scopeList;
        }
        return null;
    }

    @Override
    /**
     *@描述 用户列表
     *@参数 [params, page]
     *@返回值 com.baomidou.mybatisplus.core.metadata.IPage<com.jingli.modules.sys.user.model.UserResult>
     *@创建人 yangbin
     */
    public IPage<UserResult> getUserList(Map<String, Object> params, Page<UserResult> page) {
        List<UserResult> results = userMapper.getUserList(params, page);
        // 补充数据权限
        setUserDataScoprArr(results);
        page.setRecords(results);
        return page;
    }

    private void setUserDataScoprArr(List<UserResult> results) {
        if (CollectionUtils.isEmpty(results)) {
            return;
        }
        // 获取所有代理商
        List<OperatorSelect> allOperator = operatorService.getOperatorSelect();

        for (UserResult result : results) {
            if (result.getDataPermissions().equals(Constant.DATA_PERMISSIONS_ALL)) {
                result.setDataScopeArr("全部");
            } else if (result.getDataPermissions().equals(Constant.DATA_PERMISSIONS_SELF)) {
                result.setDataScopeArr("自己");
            } else if (result.getDataPermissions().equals(Constant.DATA_PERMISSIONS_CUSTOM)) {
                // 获取所有自定义数据权限的用户id
                List<Integer> customUId = results.stream()
                        .filter(user -> user.getDataPermissions().equals(Constant.DATA_PERMISSIONS_CUSTOM))
                        .map(UserResult::getId)
                        .collect(Collectors.toList());
                // 查询自定义权限
                List<UserDatascopePO> userDatascopePOS = userDatascopeMapper.selectList(new QueryWrapper<UserDatascopePO>().in("user_id", customUId));
                Map<Integer, List<UserDatascopePO>> userIdMap = userDatascopePOS.stream()
                        .collect(Collectors.groupingBy(UserDatascopePO::getUserId));
                List<UserDatascopePO> pos = userIdMap.get(result.getId());
                if (CollectionUtils.isEmpty(pos)) {
                    result.setDataScopeArr("");
                } else {
                    List<Integer> operIds = pos.stream().map(UserDatascopePO::getOperatorId).collect(Collectors.toList());
                    String dataArr = allOperator.stream()
                            .filter(os -> operIds.contains(os.getOperatorId()))
                            .map(OperatorSelect::getOperatorNm)
                            .collect(Collectors.joining(","));
                    result.setDataScopeArr(dataArr);
                }
            }
        }
    }

}
