package org.lizhishu.love.core;

import tk.mybatis.mapper.additional.dialect.oracle.OracleMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;


/**
 * @Author: Wangxh
 * @Date:2018/6/26 0026 10:00
 * @Description: 定制版MyBatis Mapper插件接口
 */
public interface Mapper<T>
        extends
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        OracleMapper<T> {
}
