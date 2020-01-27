package org.lizhishu.love.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * @Author: Wangxh
 * @Date:2018/6/26 0026 10:04
 * @Description: service层基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {

    /**
     * 新增
     *
     * @param model
     * @return
     */
    Integer save(T model);

    /**
     * 批量新增
     *
     * @param models
     */
    void save(List<T> models);

    /**
     * 通过id删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     *
     * @param ids eg：ids -> “1,2,3,4”
     */
    void deleteByIds(String ids);

    /**
     * 更新
     *
     * @param model
     */
    void update(T model);

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * @param fieldName
     * @param value
     * @throws TooManyResultsException
     * @return通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     */
    T findBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 通过id列表查询
     *
     * @param ids ids -> “1,2,3,4”
     * @return
     */
    List<T> findByIds(String ids);

    /**
     * 根据查询条件查询
     *
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);

    /**
     * 获取所有
     *
     * @return
     */
    List<T> findAll();
}
