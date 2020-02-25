package org.lizhishu.love.constant;

/**
 * 系统级静态变量
 * @author java1234_小锋
 * @site www.java1234.com
 * @company Java知识分享网
 * @create 2019-08-13 上午 9:51
 */
public class SystemConstant {

    /**
     * token
     */
    public static final int JWT_ERRCODE_NULL = 4000;			//Token不存在
    public static final int JWT_ERRCODE_EXPIRE = 4001;			//Token过期
    public static final int JWT_ERRCODE_FAIL = 4002;			//验证不通过

    /**
     * JWT
     */
    //密匙
    public static final String JWT_SECERT = "8677df7fc3a34e26a61c034d5ec8245d";
    //token有效时间
    public static final long JWT_TTL = 60 * 60 * 1000;


    //用户封号状态
    public static final int STATUS_DEL = 0;
    public static final int STATUS_NORMAL = 1;
    public static final int MAX_ERROR_LOGIN_NUM = 5;
}
