package com.jingli.admin.constant;


/**
 * @author lizi
 * @Description 常量类
 */
public class Constant {


    /**
     * 用户session信息KEY
     */
    public final static String USER_SESSION_KEY = "userInfo";

    /**
     * 用户信息session信息KEY
     */
    public final static String USERINFO_SESSION_KEY = "user";

    /**
     * token Secret
     */
    public final static String SECRET = "cdzSecret";

    /**
     * token过期时长  2小时
     */
    public final static Long EXPIRATION = 7200L;


    /**
     * 用户数据权限
     */
    public final static Integer DATA_PERMISSIONS_ALL = 1;
    public final static Integer DATA_PERMISSIONS_SELF = 2;
    public final static Integer DATA_PERMISSIONS_CUSTOM = 3;

    /**
     * 代理商登录
     */
    public final static Integer LOGIN_TYPE_OPERATOR = 1;
    /**
     * 运营端登录
     */
    public final static Integer LOGIN_TYPE_NOT_OPERATOR = 0;

    /**
     * 用户是代理商
     */
    public final static Integer USER_IS_OPERATOR = 1;
    /**
     * 菜单树,顶层节点
     */
    public static final Integer MENT_CODE_TOP = 0;
    /**
     * 菜单类型: 菜单
     */
    public static final Integer MENU_TYPE_MENU = 0;

    /**
     * 菜单类型: 按钮
     */
    public static final Integer MENU_TYPE_BUTTON = 1;

    /**
     * 4轮车类型
     */
    public static final Short DEVICE_TYPE_FOUR = 4;

    /**
     * 2轮车类型
     */
    public static final Short DEVICE_TYPE_TWO = 2;

    /**
     * 平台类型
     */
    public static final String PLAT_FORM = "platForm";
}

