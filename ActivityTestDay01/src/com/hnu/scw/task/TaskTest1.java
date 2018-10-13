package com.hnu.scw.task;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author scw
 * @create 2018-01-16 10:13
 * @desc 针对任务的第一情况进行分析
 * 当没有进入该节点之前，就可以确定任务的执行人
    实例：比如进行“请假申请”的流程时候，最开始执行的就是提交”请假申请“，
     那么就需要知道，谁提交的“请假”，很明显，在一个系统中，谁登陆到系统里面，
     谁就有提交“请假任务”的提交人，那么执行人就可以确定就是登录人。
 **/
public class TaskTest1 {

    /**
     * 部署流程
     */
    @Test
    public void startDeployTest(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
                .createDeployment()
                .name("请假流程：情况一")
                .addClasspathResource("com/hnu/scw/task/shenqing.bpmn")
                .addClasspathResource("com/hnu/scw/task/shenqing.png")
                .deploy();
    }

    /**
     * 启动流程实例
     *    可以设置一个流程变量
     */
    @Test
    public void testStartPI(){
        /**
         * 流程变量
         *   给<userTask id="请假申请" name="请假申请" activiti:assignee="#{student}"></userTask>
         *     的student赋值
         */
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("student", "小明");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRuntimeService()
                .startProcessInstanceById("shenqing1:1:1304",variables);
    }
    /**
     * 在完成请假申请的任务的时候，给班主任审批的节点赋值任务的执行人
     */
    @Test
    public void testFinishTask_Teacher(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("teacher", "我是小明的班主任");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete("1405", variables); //完成任务的同时设置流程变量
    }

    /**
     * 在完成班主任审批的情况下，给教务处节点赋值
     */
    @Test
    public void testFinishTask_Manager(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("manager", "我是小明的教务处处长");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete("1603", variables); //完成任务的同时设置流程变量
    }

    /**
     * 结束流程实例
     */
    @Test
    public void testFinishTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete("1703");
    }

}
