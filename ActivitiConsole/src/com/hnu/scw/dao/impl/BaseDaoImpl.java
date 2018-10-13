package com.hnu.scw.dao.impl;

import com.hnu.scw.dao.BaseDao;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

/**
 * @author scw
 * @create 2017-12-21 9:25
 * @desc 基本dao层的实现
 **/
public class BaseDaoImpl<T> implements BaseDao<T>{
    @Autowired
    private SessionFactory sessionFactory;
    /**
    获取加载到调用者的类型
     */
    protected Class<T> clazz;

    public BaseDaoImpl(){
        //获得当前类型的带有泛型类型的父类
        ParameterizedType genericSuperclass =(ParameterizedType)getClass().getGenericSuperclass();
        //获得运行期的泛型类型
        clazz   = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    /**
     * 获取当前线程的session对象，用于操作数据库
     * @return
     */
    public Session getSession(){
        try {
            return sessionFactory.getCurrentSession();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long save(T t) {
        return (Long) getSession().save(t);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public T load(Serializable id) {
        return getSession().load( clazz, id);
    }

    @Override
    public void delete(Serializable id) {
        getSession().delete(id);
    }

    @Override
    public Integer findTotalCount(String hql, Object[] paras) {
        Query query = getSession().createQuery(hql);
        //设置参数参数(如果是空，那么就不需要进行设置参数，就是查询所有)
        setFindParamter( query ,paras);
        //进行查询
        List<Object> list = query.list();
        if (list.size() == 0) {
            return 0;
        } else {
            return Integer.valueOf(list.get(0).toString());
        }
    }

    @Override
    public List<T> findQueryMOdel(String hql, Object[] paras) {
        Session session = this.getSession();
        Query query = session.createQuery(hql);
        //设置查询where参数
        setFindParamter(query , paras);
        return query.list();
    }

    /**
     * 设置查询语句的参数
     * @param query
     * @param paras
     */
    private void setFindParamter(Query query, Object[] paras) {
        if (ArrayUtils.isNotEmpty(paras)) {
            int j = 0;
            for (int i = 0; i < paras.length; i++) {
                //如果是集合，就使用下面if中的方法，如果是数组类型的参数，就用else中的方法
                if (paras[i] instanceof Collection<?>) {
                    query.setParameterList("list", (Collection<?>) paras[i]);
                } else {
                    query.setParameter(j, paras[i]);
                    j++;
                }
            }
        }
    }
}
