package com.jingli.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jingli.admin.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DESCRIPTION :
 *
 * @author ducf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("xt_menu")
public class MenuPO implements Serializable {

    @TableId
    private Integer id;
    /**
     * 菜单code
     */
    private Integer menuCode;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 父级菜单code
     */
    private Integer parentCode;

    /**
     * 父级菜单名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 序号
     */
    private Integer sequence;
    /**
     * 菜单类型
     */
    private Integer menuType;

    /**
     * 后台请求地址
     */
    private String requestUrl;

    /**
     * 前台路由
     */
    private String frontendUrl;

    private Date createTime;
    private Date updateTime;

    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<MenuPO> children;

    public static List<MenuPO> setParentNames(List<MenuPO> allMenus, List<MenuPO> menuPOS) {
        Map<Integer, String> menuMap = allMenus.stream().collect(Collectors.toMap(MenuPO::getMenuCode, MenuPO::getMenuName));
        menuPOS.stream().forEach(menu -> {
            if (menu.getParentCode() != 0) {
                menu.setParentName(menuMap.get(menu.getParentCode()));
            }
        });
        return menuPOS;
    }

    public boolean isMenu() {
        if (Constant.MENU_TYPE_MENU.equals(this.menuType)) {
            return true;
        }
        return false;
    }

    public boolean isButton() {
        if (Constant.MENU_TYPE_BUTTON.equals(this.menuType)) {
            return true;
        }
        return false;
    }
}
