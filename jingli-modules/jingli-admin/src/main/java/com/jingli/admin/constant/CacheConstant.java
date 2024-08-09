package com.jingli.admin.constant;

/**
 * DESCRIPTION :redis 缓存,区分标识
 *
 * @author Zfenor
 */
public interface CacheConstant {

    /**
     * 用户
     */
    interface User {
        // 用户的角色 cache前缀 example  menu:userId:1340 = [{},{}]
        String PREFIX_NAME = "user:name:";
    }

    /**
     * 角色
     */
    interface Role {
        String PREFIX = "role:";
        // 用户的角色 cache前缀 example  menu:userId:1340 = [{},{}]
        String PREFIX_USER = "role:userId:";
        // 所有角色
        String ALL = "role:all";
    }

    /**
     * 菜单
     */
    interface Menu {

        public String PREFIX = "menu:";
        // 角色的菜单cache前缀    example  menu:roleCode:11 = [{},{}]
        String PREFIX_ROLE = "menu:roleCode:";
        // 所有角色
        String ALL = "menu:all";

        // 用户的菜单  example  menu:menu:userId:1340 = [{},{}]
        String PREFIX_MENU_USER = "menu:menu:userId:";

        // 用户的按钮  example  menu:menu:userId:1340 = [{},{}]
        String PREFIX_BUTTON_USER = "menu:button:userId:";
    }


}
