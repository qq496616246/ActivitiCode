package com.hnu.scw.dao;

import com.hnu.scw.model.Employee;
import java.util.List;

public interface EmplyoeeDao extends BaseDao<Employee> {
    /**
     * 获取到相应内容的用户数据库中的表信息
     * @param employee
     * @return
     */
     List<Employee> findListEmployee(Employee employee);

    /**
     * 根据相对应的字段查找对应的用户信息
     * @param employee
     * @return
     */
     Employee findEmployee(Employee employee);
}
