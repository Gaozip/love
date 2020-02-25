package org.lizhishu.love.controller;

import org.lizhishu.love.core.Result;
import org.lizhishu.love.core.ResultGenerator;
import org.lizhishu.love.entity.Menu;
import org.lizhishu.love.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @Author: gaozp
* @Date: 2020:02:23 21:59:45
* @Description:
*/
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    /**
    *  新增
    * @return
    */
    @PostMapping
    public Result add(@RequestBody Menu menu) {
        menu.setCreateById(1);
        menu.setCreateByTime(new Date());
        menu.setIsValid(1);
        System.out.println(menu.toString());
//        menuService.save(menu);
        return ResultGenerator.genSuccessResult();
    }

    /**
    *  删除
    * @return
    */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        menuService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
    *  更新
    * @return
    */
    @PutMapping
    public Result update(@RequestBody Menu menu) {
        menuService.update(menu);
        return ResultGenerator.genSuccessResult();
    }

    /**
    *  详情
    * @return
    */
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Menu menu = menuService.findById(id);
        return ResultGenerator.genSuccessResult(menu);
    }

    /**
    *  列表
    * @return
    */
//    @GetMapping
//    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<Menu> list = menuService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }
}
