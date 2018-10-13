package com.hnu.scw.service.imp;

import com.hnu.scw.dao.EmplyoeeDao;
import com.hnu.scw.model.Employee;
import com.hnu.scw.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author scw
 * @create 2018-01-27 19:01
 * @desc 用户操作的service
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmplyoeeDao emplyoeeDao;

    @Override
    public Employee findEmpyolee(Employee employee) {
        return emplyoeeDao.findEmployee(employee);
    }
}
