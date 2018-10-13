package com.hnu.scw.service.imp;

import com.hnu.scw.dao.PersonDao;
import com.hnu.scw.model.Person;
import com.hnu.scw.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @create 2017-12-19 16:01
 * @desc
 **/
@Service
public class PersonServiceImp1 implements PersonService {
    @Autowired
    private PersonDao personDao;

    /**
     * 当添加了一条数据之后，返回添加的那条数据对应的ID
     * @param person
     * @return
     */
    @Override
    public Long savePerson(Person person) {
        Long addId = personDao.save(person);
        return addId;
    }

    @Override
    public void updatePerSon(Person person) {
        personDao.update(person);
    }

    @Override
    public Person loadPersonById(Long id) {
        return personDao.load(id);
    }

    @Override
    public Integer findTotalPersonNumber(Person person) {
        Integer personTotalNumber = personDao.findPersonTotalNumber(person);
        return personTotalNumber;
    }


}
