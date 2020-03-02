package org.lizhishu.love.controller;

import org.lizhishu.love.constant.SystemConstant;
import org.lizhishu.love.core.Result;
import org.lizhishu.love.core.ResultGenerator;
import org.lizhishu.love.entity.User;
import org.lizhishu.love.service.UserService;
import org.lizhishu.love.utils.JwtUtils;
import org.lizhishu.love.utils.WebUtil;
import org.lizhishu.love.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: gzip
 * @Date: 2020/2/16  15:50
 * @Description:
 **/
@RestController
@RequestMapping("/")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/loginExt")
    public Result doLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody User userExt) {

        //用户名及密码非空验证
        if (WebUtil.equalsNull(userExt.getUserName()) || WebUtil.equalsNull(userExt.getPassword())) {
            logger.error("用户名:[" + userExt.getUserName() + "],密码:[" + userExt.getPassword() + "]登录失败：用户名或者密码错误");
            return ResultGenerator.genFailResult("用户名或者密码错误");
        }

        User user = this.userService.findByUserName(userExt.getUserName());

        if(user == null){
            return ResultGenerator.genFailResult("该用户不存在！");
        }else{
            //用户删除确认
            if (user.equals(null) || user.getStatus() == SystemConstant.STATUS_DEL) {
                logger.error("用户名:[" + userExt.getUserName() + "],密码:[" + userExt.getPassword() + "]登录失败：用户名或者密码错误");
                return ResultGenerator.genFailResult("用户名或者密码错误");

            }
            //用户被封号确认
            if (user.getStatus() != SystemConstant.STATUS_NORMAL) {
                logger.error("用户名:[" + userExt.getUserName() + "],密码:[" + userExt.getPassword() + "]登录失败：用户已禁止登录，请联系管理员");
                return ResultGenerator.genFailResult("用户已禁止登录，请联系相关客服人员");
            }

            //校验密码
            if (!this.validPasswd(userExt.getPassword(), user.getPassword())) {
                user.setCycleCountByError(user.getCycleCountByError() + 1);
                if(user.getCycleCountByError() >= 5){
                    user.setIsLockedByErrorLogin(1);
                    return ResultGenerator.genFailResult("该账号因多次登录错误被锁定!");

                }
                return ResultGenerator.genFailResult("密码错误");
            }

            try {
                afterLogin(user);
                this.updateUserLoginInfo(request, user);
                logger.info("用户名:[" + user.getUserName() + "],密码:[" + user.getPassword() + "]登录成功");
                String JWT = JwtUtils.createJWT("1", user.getUserName(), SystemConstant.JWT_TTL);
                logger.info(JWT);
                return ResultGenerator.genSuccessResult(JWT);
            } catch (Exception e) {
                logger.error("用户登录出现异常，请联系管理员", e);
                String errorMsg = "用户登录出现异常，请联系管理员";
                return ResultGenerator.genFailResult(errorMsg);
            }
        }
    }

    private Boolean validPasswd(String password1,String password2){
        return MD5Util.md5(password1).equals(password2);
    }

    /**
     * 登录成功后清除错误计数
     *
     * @param user
     */
    private void afterLogin(User user) {
        user.setCycleCountByError(0);
        user.setIsLockedByErrorLogin(0);
        this.userService.update(user);
    }

    /**
     * 更新用户登录信息
     *
     * @param request
     * @param user
     */
    private void updateUserLoginInfo(HttpServletRequest request, User user) {
        user.setLoginIp(WebUtil.getIp(request));
        user.setLastLoginTime(new Date());
        this.userService.update(user);
    }

    /**
     * 设置用户密码错误显示信息
     *
     * @param user
     * @return
     */
    private String checkAndSetError(User user) {

        if (user.getLastLoginTime() == null) {
            user.setLastLoginTime(new Date());
        }
        String result;
        Long mi = ((new Date()).getTime() - user.getLastLoginTime().getTime()) / 60000L;
        if (null != user.getIsLockedByErrorLogin() && 1 == user.getIsLockedByErrorLogin() && mi < 30L) {
            Long lessTime = 30L - mi;
            result = "登录错误次数过多，请于" + lessTime + "分钟后重试";
        } else {
            result = this.doError(user, mi < 30L);
        }
        return result;
    }


    private String doError(User user, boolean isInTime) {
        Integer errorNum = SystemConstant.MAX_ERROR_LOGIN_NUM;
        String result = "用户名或密码错误";

        //是否30分钟内第一次错误
        if (isInTime) {
            if (user.getCycleCountByError() + 1 >= errorNum) {
                user.setIsLockedByErrorLogin(1);
                result = "登录错误次数过多，请于30分钟后重试";
            } else if (user.getCycleCountByError() + 1 >= 3) {
                result = "登录错误次数过多，您还有" + (errorNum - user.getCycleCountByError() - 1) + "次尝试机会";
            } else {
                result = "用户名或密码错误";
            }

            user.setCycleCountByError(user.getCycleCountByError() + 1);
        } else {
            result = "用户名或密码错误";
            user.setCycleCountByError(1);
            user.setIsLockedByErrorLogin(0);
        }

        user.setLastLoginTime(new Date());

        this.userService.update(user);
        return result;
    }
}
