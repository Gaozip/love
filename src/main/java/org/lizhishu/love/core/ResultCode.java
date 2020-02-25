package org.lizhishu.love.core;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAIL(400),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401),
    /**
     * 接口不存在
     */
    NOT_FOUND(404),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500),
    /**
     * 无session
     */
    NO_SESSION(600),
    /**
     * session 过期
     */
    SESSION_TIME_OUT(601),
    /**
     * Token不存在
     */
    JWT_ERRCODE_NULL(4000),
    /**
     * Token过期
     */
    JWT_ERRCODE_EXPIRE(4001),
    /**
     * 验证不通过
     */
    JWT_ERRCODE_FAIL(4002);


    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
