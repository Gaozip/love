package org.lizhishu.love.controller;

import io.jsonwebtoken.Claims;
import org.lizhishu.love.constant.SystemConstant;
import org.lizhishu.love.core.Result;
import org.lizhishu.love.core.ResultGenerator;
import org.lizhishu.love.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: gzip
 * @Date: 2020/2/26  9:44
 * @Description:
 **/
@RestController
@RequestMapping("/")
public class TokenController {

    private static Logger logger = LoggerFactory.getLogger(TokenController.class);

    /**
     * 刷新用户token
     * @param request
     * @return
     */
    @GetMapping(value = "/refreshToken")
    public Result refreshToken(HttpServletRequest request){
        Claims claims = JwtUtils.validateJWT(request.getHeader("token")).getClaims();
        String JWT = JwtUtils.createJWT(claims.getId(),claims.getSubject(), SystemConstant.JWT_TTL);
        logger.info("新token"+JWT);
        return ResultGenerator.genSuccessResult(JWT);
    }
}
