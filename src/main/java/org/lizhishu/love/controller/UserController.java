package org.lizhishu.love.controller;

import org.lizhishu.love.core.Result;
import org.lizhishu.love.core.ResultGenerator;
import org.lizhishu.love.entity.User;
import org.lizhishu.love.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* @Author: gaozp
* @Date: 2020:01:26 19:47:40
* @Description:
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
    *  新增
    * @return
    */
    @PostMapping
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    /**
    *  删除
    * @return
    */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
    *  更新
    * @return
    */
    @PutMapping
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    /**
    *  详情
    * @return
    */
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    /**
    *  列表
    * @return
    */
//    @GetMapping
//    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<User> list = userService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }
}
