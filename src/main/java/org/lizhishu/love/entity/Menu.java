package org.lizhishu.love.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "sys_menu")
@Data
public class Menu {
    @Id
    @Column(name = "MENU_ID")
    private Integer menuId;

    /**
     * 菜单名称
     */
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 菜单标识
     */
    @Column(name = "MENU_CODE")
    private String menuCode;

    /**
     * 图标名称
     */
    @Column(name = "ICON_NAME")
    private String iconName;

    /**
     * 菜单路由
     */
    @Column(name = "MENU_ROUTER")
    private String menuRouter;

    /**
     * 菜单介绍
     */
    @Column(name = "MENU_DESC")
    private String menuDesc;

    @Column(name = "CREATE_BY_TIME")
    private Date createByTime;

    /**
     * 创建人
     */
    @Column(name = "CREATE_BY_ID")
    private Integer createById;

    /**
     * 是否有效
     */
    @Column(name = "IS_VALID")
    private Integer isValid;
}