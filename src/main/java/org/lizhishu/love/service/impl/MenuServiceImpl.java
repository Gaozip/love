package org.lizhishu.love.service.impl;

import org.lizhishu.love.dao.MenuMapper;
import org.lizhishu.love.entity.Menu;
import org.lizhishu.love.service.MenuService;
import org.lizhishu.love.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* @Author: gaozp
* @Date: 2020:02:23 21:59:45
* @Description:
*/
@Service
public class MenuServiceImpl extends AbstractService<Menu> implements MenuService {
    @Resource
    private MenuMapper sysMenuMapper;

}
