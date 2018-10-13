package com.hnu.scw.service;

import com.hnu.scw.model.Employee;

public interface EmployeeService {

    /**
     * 获取到相应的用户信息
     * @param employee
     * @return
     */
    public Employee findEmpyolee(Employee employee);
}
