package com.hnu.scw.controller;

import com.hnu.scw.model.Person;
import com.hnu.scw.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author scw
 * @create 2017-12-19 16:44
 * @desc
 **/
@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    /**
     * 保存测试
     * @return
     */
    @RequestMapping(value = "/person")
    public String testDemo(){
        Person person = new Person();
        person.setName("我是8888的");
        person.setSex("男");
        personService.savePerson(person);
        return "hello";
    }

    /**
     * 更新测试
     * @return
     */
    @RequestMapping(value = "/updateperson")
    public String updateDemo(){
        Person person = new Person();
        person.setSex("哈哈");
        person.setName("我是更新操作");
        person.setId(1L);
        personService.updatePerSon(person);
        return "hello";
    }

    /**
     * 获取数据测试
     * @return
     */
    @RequestMapping(value = "getperson")
    public String loadDemo(Map<String , Object> map){
        Person person = personService.loadPersonById(2L);
        System.out.println(person);
        map.put("person" , person);
        return "hello";
    }

    /**
     * 获取特殊查询的数据条目数测试
     * @return
     */
    @RequestMapping(value = "totalperson")
    public String getTotalDemo(){
        Person person = new Person();
        person.setSex("女");
        Integer totalPersonNumber = personService.findTotalPersonNumber(person);
        System.out.println("查询的数据条目为："+totalPersonNumber);
        return "hello";
    }
}
