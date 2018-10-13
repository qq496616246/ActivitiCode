package com.hnu.scw.test;

import com.hnu.scw.model.Person;
import com.hnu.scw.service.PersonService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @create 2017-12-19 17:55
 * @desc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProjectTest {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Test
    //单独测试hibernate
    public void fun1(){
        Configuration conf = new Configuration().configure();
        SessionFactory sf = conf.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        //-------------------------------------------------
        Person u = new Person();
        u.setName("hibernate");
        u.setSex("中");
        session.save(u);
        //-------------------------------------------------
        tx.commit();
        session.close();
        sf.close();
    }

    /**
     * 单独测试spring容器产生sessionFactory
     */
    @Test
    public void fun2(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Person u = new Person();
        u.setName("hibernate33333333333");
        u.setSex("中");
        session.save(u);
        tx.commit();
        session.close();
    }

    /**
     * 测试spring和hibernate的整合
     */
    @Test
    public void fun3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonService personService = (PersonService) applicationContext.getBean("personService");
        Person person = new Person();
        person.setSex("男");
        person.setName("我是一个好学生123123123");
        personService.savePerson(person);
    }

    /**
     * 注解bean的注入测试
     */
    @Autowired
    private PersonService personService;
    @Test
    public void fun4(){
        Person person = new Person();
        person.setSex("男");
        person.setName("我是一个好学生3333333333333");
        personService.savePerson(person);
    }
}
