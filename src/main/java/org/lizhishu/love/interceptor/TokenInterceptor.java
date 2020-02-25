package org.lizhishu.love.interceptor;

import org.lizhishu.love.constant.SystemConstant;
import org.lizhishu.love.core.CheckResult;
import org.lizhishu.love.core.ResultCode;
import org.lizhishu.love.core.ResultGenerator;
import org.lizhishu.love.utils.WebUtil;
import org.lizhishu.love.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: gzip
 * @Date: 2020/2/10  9:50
 * @Description:
 **/
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getRequestURI();
        System.out.println("路径:"+contextPath);
        if (handler instanceof HandlerMethod){
            String authHeader = request.getHeader("token");
            if (WebUtil.equalsNull(authHeader)) {
                logger.error("验证失败");
                print(response, ResultGenerator.genResult(ResultCode.JWT_ERRCODE_NULL,"签名验证不存在"));
                return false;
            }else{
                //验证JWT的签名，返回CheckResult对象
                CheckResult checkResult = JwtUtils.validateJWT(authHeader);

                if (checkResult.isSuccess()) {
                    logger.info("签名验证通过");
                    return true;
                } else {
                    switch (Integer.valueOf(checkResult.getErrCode().toString())) {
                        // 签名验证不通过
                        case SystemConstant.JWT_ERRCODE_FAIL:
                            logger.error("签名验证不通过");
                            print(response, ResultGenerator.genResult(checkResult.getErrCode(),"签名验证不通过"));
                            break;
                        // 签名过期，返回过期提示码
                        case SystemConstant.JWT_ERRCODE_EXPIRE:
                            logger.error("签名过期");
                            print(response,ResultGenerator.genResult(checkResult.getErrCode(),"签名过期"));
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            }
        }else{
            return true;
        }
    }

    public void print(HttpServletResponse response, Object message){
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            PrintWriter writer = response.getWriter();
            writer.write(message.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
