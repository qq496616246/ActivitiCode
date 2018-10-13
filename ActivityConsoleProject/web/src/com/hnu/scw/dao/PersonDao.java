package com.hnu.scw.dao;

import com.hnu.scw.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends BaseDao<Person>{
    /**
     * 获取符合查询条件的person的数目
     * @param person
     * @return
     */
    public Integer findPersonTotalNumber(Person person);
}
