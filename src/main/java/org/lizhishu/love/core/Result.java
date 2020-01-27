package org.lizhishu.love.core;

import com.alibaba.fastjson.JSON;

/**
 * @Date:2018/6/26 0026 10:01
 * @Description: 响应结果封装
 */
public class Result {
    /**
     * 状态码
     */
    private int code;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
