package com.hnu.scw.dao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 最基础的basedao接口的设计
 * @param <T>
 */
@Repository
public interface BaseDao<T>{

    /**
     * 保存数据信息
     * @param t 参数是泛型，这样就具有通用性
     */
    public Long save(T t);

    /**
     * 更新数据操作
     * @param t
     */
    public void update(T t);

    /**
     * 获取到对应id主键的对象内容
     * @param id
     * @return
     */
    public T load(Serializable id);

    /**
     * 获取对应查询内容的数据条数
     * @param hql
     * @param paras
     * @return
     */
    public Integer findTotalCount(String hql , Object[] paras);
}
