package com.hnu.scw.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * @author scw
 * @create 2018-01-15 11:06
 * @desc 从数据源和流程图中，生成一个数据库表（这个就是Activiti流程控制的关键的数据表）
 **/
public class ActivitiTable {
    /**
     * 创建Activiti流的相关的数据库表
     */
    @Test
    public void creatTable(){
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
    }

}
