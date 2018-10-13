package com.hnu.scw.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProcessEngineTest {
	/**
	 * 测试生成activiti的相关数据表
	 */
	@Test
	public void testProcessEngine(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.getBean("processEngine");
	}
}
