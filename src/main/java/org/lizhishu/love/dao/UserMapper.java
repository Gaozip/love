package org.lizhishu.love.dao;

import org.lizhishu.love.core.Mapper;
import org.lizhishu.love.entity.User;

public interface UserMapper extends Mapper<User> {
    User findByUserName(String userName);
}