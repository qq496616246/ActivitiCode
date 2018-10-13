package com.hnu.scw.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * @author scw
 * @create 2018-01-15 16:31
 * @desc 对于流程实例的相关API的示例（重点）
 **/
public class ActivitiProcessInstanceTest {
    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcessInstanceByPID(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceById("shenqing:1:804");
        System.out.println(processInstance.getId());
    }
    /**
     * 根据pdkey启动流程实例，默认启动最高版本的
     */
    @Test
    public void testStartPIByPDKEY(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRuntimeService()
                .startProcessInstanceByKey("shenqing");
    }
    /**
     * 完成任务
     */
    @Test
    public void testFinishTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete("904");
    }

    /**
     * 查询任务
     *   根据任务的执行人查询正在执行任务(通过act_ru_task数据表)
     */
    @Test
    public void testQueryTaskByAssignee(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        /**
         * 当前班主任小毛人这个人当前正在执行的所有的任务
         */
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .orderByTaskCreateTime()
                .desc()
                .taskAssignee("小毛")
                .list();
        for (Task task : tasks) {
            System.out.println(task.getName());
            System.out.println(task.getAssignee());
        }
    }

    /**
     * 查询所有的正在执行的任务
     */
    @Test
    public void testQueryTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .list();
        for (Task task : tasks) {
            System.out.println(task.getName());
        }
    }

    /**
     * 根据piid查询任务
     */
    @Test
    public void testQueryTaskByPIID(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .executionId("901")
                .list();
        for (Task task : tasks) {//因为没有并发，所以就有一个
            System.out.println(task.getName());
        }
    }
    /**
     * 根据piid得到当前正在执行的流程实例的正在活动的节点
     */
    @Test
    public void testActivity(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        /**
         * 根据piid得到流程实例
         */
        ProcessInstance pi = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId("1201")
                .singleResult();
        String activityId = pi.getActivityId();//当前流程实例正在执行的activityId
        System.out.println(activityId);
    }

    /**
     * 查看已经完成的任务和当前在执行的任务
     */
    @Test
    public void findHistoryTask(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        //如果只想获取到已经执行完成的，那么就要加入completed这个过滤条件
        List<HistoricTaskInstance> historicTaskInstances1 = defaultProcessEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .taskDeleteReason("completed")
                .list();
        //如果只想获取到已经执行完成的，那么就要加入completed这个过滤条件
        List<HistoricTaskInstance> historicTaskInstances2 = defaultProcessEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .list();
        System.out.println("执行完成的任务：" + historicTaskInstances1.size());
        System.out.println("所有的总任务数（执行完和当前未执行完）：" +historicTaskInstances2.size());
    }
}
