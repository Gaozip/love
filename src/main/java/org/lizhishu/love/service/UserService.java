package org.lizhishu.love.service;
import org.lizhishu.love.core.Service;
import org.lizhishu.love.entity.User;


/**
* @Author: gaozp
* @Date: 2020:01:26 19:47:40
* @Description:
*/
public interface UserService extends Service<User> {

    User findByUserName(String userName);
}
