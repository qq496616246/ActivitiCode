package com.hnu.scw.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * @author scw
 * @create 2018-01-23 15:45
 * @desc 关于对于组任务的测试内容
 **/
public class GroupTaskTest {
    /**
     * 主要是对于某些任务流程中，有N个人，但是只需要其中的某一个通过，
     * 则该任务就通过了，所以针对这样的业务需求，就有如下的内容
     */
    @Test
    public void deployTashTest(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("com/hnu/scw/test/task3.bpmn")
                .addClasspathResource("com/hnu/scw/test/task3.png")
                .name("组任务的测试")
                .deploy();
    }
    /**
     * 当启动完流程实例以后，进入了"电脑维修"节点，该节点是一个组任务
     *    这个时候，组任务的候选人就会被插入到两张表中
     *       act_ru_identitylink  存放的是当前正在执行的组任务的候选人
     *       act_hi_identitylink
     */
    @Test
    public void processTaskStartTest(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRuntimeService()
                .startProcessInstanceByKey("task3");
    }
    /**
     * 对于act_hi_identitylink表，根据任务ID，即TASK_ID字段查询候选人
     */
    @Test
    public void testQueryCandidateByTaskId(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<IdentityLink> identityLinks = processEngine.getTaskService()
                .getIdentityLinksForTask("2104");
        for (IdentityLink identityLink : identityLinks) {
            System.out.println(identityLink.getUserId());
        }
    }

    /**
     * 对于act_hi_identitylink表，根据候选人,即USER_ID_查看组任务
     */
    @Test
    public void testQueryTaskByCandidate(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .taskCandidateUser("工程师1")
                .list();
        for (Task task : tasks) {
            System.out.println(task.getName());
        }
    }
    /**
     * 候选人中的其中一个人认领任务
     */
    @Test
    public void testClaimTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                /**
                 * 第一个参数为taskId
                 * 第二个参数为认领人
                 */
                .claim("2104", "工程师2");
    }

}
