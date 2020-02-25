package org.lizhishu.love.core;

/**
 * @Author: gzip
 * @Date: 2020/2/16  0:30
 * @Description:
 **/

import io.jsonwebtoken.Claims;

public class CheckResult {

    private ResultCode errCode;

    private boolean success;

    private Claims claims;

    public ResultCode getErrCode() {
        return errCode;
    }

    public void setErrCode(ResultCode errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

}
