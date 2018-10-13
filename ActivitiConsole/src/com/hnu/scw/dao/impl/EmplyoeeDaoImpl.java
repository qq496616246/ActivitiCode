package com.hnu.scw.dao.impl;

import com.hnu.scw.dao.EmplyoeeDao;
import com.hnu.scw.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scw
 * @create 2018-01-27 18:55
 * @desc 用户的操作
 **/
@Repository
public class EmplyoeeDaoImpl extends BaseDaoImpl<Employee> implements EmplyoeeDao{


    @Override
    public List<Employee> findListEmployee(Employee employee) {
        String hql = "FROM Employee n where 1 = 1";
        StringBuilder sb = new StringBuilder(hql);
        List<Object> parames = new ArrayList<Object>();
        if(employee != null){
            if(StringUtils.isNotBlank(employee.getName())){
                sb.append("and n.name = ?");
                parames.add(employee.getName());
            }
        }
        return super.findQueryMOdel(sb.toString() , parames.toArray());
    }

    @Override
    public Employee findEmployee(Employee employee) {
        List<Employee> listEmployee = this.findListEmployee(employee);
        if(listEmployee != null && listEmployee.size() > 0){
            return listEmployee.get(0);
        }
        return null;
    }
}
