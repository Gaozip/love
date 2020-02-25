package org.lizhishu.love.service.impl;

import org.lizhishu.love.dao.UserMapper;
import org.lizhishu.love.entity.User;
import org.lizhishu.love.service.UserService;
import org.lizhishu.love.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* @Author: gaozp
* @Date: 2020:01:26 19:47:40
* @Description:
*/
@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper sysUserMapper;

    @Override
    public User findByUserName(String userName) {
        System.out.println(userName);
        return sysUserMapper.findByUserName(userName);
    }
}
