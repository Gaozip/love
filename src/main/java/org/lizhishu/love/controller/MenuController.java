package org.lizhishu.love.controller;

import org.lizhishu.love.core.Result;
import org.lizhishu.love.core.ResultGenerator;
import org.lizhishu.love.entity.Menu;
import org.lizhishu.love.service.MenuService;
import org.lizhishu.love.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Author: gaozp
* @Date: 2020:02:23 21:59:45
* @Description:
*/
@RestController
@RequestMapping("/menu")
public class MenuController {

    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

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
        menuService.save(menu);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 校验菜单是否被注册过
     * @param menu
     * @return
     */
    @PostMapping("/checkMenuExist")
    public Result checkMenuExist(@RequestBody Menu menu){

        if(WebUtil.equalsNull(menu.getMenuCode()) || WebUtil.equalsNull(menu.getMenuRouter())){
            return ResultGenerator.genFailResult("菜单编号或菜单路由不存在!");
        }
        try {
            Integer num = this.menuService.checkMenuExist(menu);
            Map<String,Integer> map = new HashMap<>();
            map.put("checkExist",num);
            return ResultGenerator.genSuccessResult(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultGenerator.genFailResult("业务逻辑失败!请联系管理员!");
        }
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
    @GetMapping
    public Result list() {
        List<Menu> list = menuService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 根据菜单标识删除
     * @param menuCode
     */
    @DeleteMapping("/{menuCode}")
    public Result deleteByMenuCode(@PathVariable String menuCode){
        try {
            this.menuService.deleteByMenuCode(menuCode);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultGenerator.genFailResult("业务处理失败!请联系管理员!");
        }
        return ResultGenerator.genSuccessResult();
    }
}
