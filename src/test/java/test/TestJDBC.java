package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lizhishu.love.LoveApplication;
import org.lizhishu.love.core.Builder;
import org.lizhishu.love.entity.User;
import org.lizhishu.love.service.UserService;
import org.lizhishu.love.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @Author: gzip
 * @Date: 2020/1/26  14:18
 * @Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoveApplication.class)
public class TestJDBC {

    @Autowired
    private UserService userService;

    @Test
    public void testSave(){
        User user = Builder.of(User::new)
                .with(User::setUserName, "zhangsan")
                .with(User::setPassword, MD5Utils.md5("11111"))
                .with(User::setRealName, "张三")
                .with(User::setBirthday, new Date(1997, 2, 19))
                .with(User::setEmail, "2982824385@qq.com")
                .with(User::setCreateTime, new Date())
                .with(User::setIsAdmin, 0)
                .with(User::setIsLockedByErrorLogin, 0)
                .with(User::setPhone, "17749071547")
                .with(User::setUserType, 0)
                .build();
        userService.save(user);
    }



    @Test
    public void testQueryAll(){
        List<User> allUser = userService.findAll();
        for (User user : allUser){
            System.out.println(user.toString());
        }

    }

    @Test
    public void doUpdate(){
        User user = userService.findById(1);
        user.setRealName("李四");
        userService.update(user);
    }
}
