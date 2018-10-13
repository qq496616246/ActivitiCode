package com.hnu.scw.service;

import com.hnu.scw.model.Person;

public interface PersonService {
    public Long savePerson(Person person);

    public void updatePerSon(Person person);

    public Person loadPersonById(Long id);

    public Integer findTotalPersonNumber(Person person);

}
