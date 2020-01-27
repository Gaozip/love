package org.lizhishu.love.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "sys_user")
@Data
public class User {
    /**
     * 用户表主键ID
     */
    @Id
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 用户名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 真实名字
     */
    @Column(name = "REAL_NAME")
    private String realName;

    /**
     * 出生年月
     */
    @Column(name = "BIRTHDAY")
    private Date birthday;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 联系电话
     */
    @Column(name = "PHONE")
    private String phone;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 账户创建者ID
     */
    @Column(name = "CREATE_USER_ID")
    private Integer createUserId;

    /**
     * 登录IP
     */
    @Column(name = "LOGIN_IP")
    private String loginIp;

    /**
     * 最后一次登录时间
     */
    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 用户类型
     */
    @Column(name = "USER_TYPE")
    private Integer userType;

    /**
     * 是不是管理员
     */
    @Column(name = "IS_ADMIN")
    private Integer isAdmin;

    /**
     * 是否因错误登录而锁定
     */
    @Column(name = "IS_LOCKED_BY_ERROR_LOGIN")
    private Integer isLockedByErrorLogin;

    /**
     * 一个周期内错误登录的次数（如果登录成功时，重置为0；超过周期时间，登录第一次失败，重置为1；周期时间内，错误登录次数累加。）
     */
    @Column(name = "CYCLE_COUNT_BY_ERROR")
    private Integer cycleCountByError;

    /**
     * 最后一次修改密码时间
     */
    @Column(name = "LAST_CHANGE_PASSWORD_TIME")
    private Date lastChangePasswordTime;
}