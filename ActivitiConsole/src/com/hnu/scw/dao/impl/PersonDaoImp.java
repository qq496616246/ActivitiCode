package com.hnu.scw.dao.impl;

import com.hnu.scw.dao.PersonDao;
import com.hnu.scw.model.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scw
 * @create 2017-12-19 15:17
 * @desc
 **/
@Repository
public class PersonDaoImp  extends BaseDaoImpl<Person> implements PersonDao{

    @Override
    public Integer findPersonTotalNumber(Person person) {
        String hql ="select count(*) from Person p where 1=1";
        StringBuilder sb = new StringBuilder(hql);
        List<Object> paras = new ArrayList<Object>();
        if(StringUtils.isNotBlank(person.getName())){
            sb.append("and p.name = ?");
            paras.add(person.getName());
        }
        if(StringUtils.isNotBlank(person.getSex())){
            sb.append("and p.sex = ?");
            paras.add(person.getSex());
        }
        return super.findTotalCount(sb.toString() ,paras.toArray() );
    }
}
