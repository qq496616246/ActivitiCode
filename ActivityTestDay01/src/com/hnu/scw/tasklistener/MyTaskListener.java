package com.hnu.scw.tasklistener;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author Administrator
 * @create 2018-01-16 11:10
 * @desc tack任务的监听，主要是为了动态分配执行人
 **/
public class MyTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        /**
         * 任务的执行人可以动态的赋值
         *   1、流程变量
         *        可以通过提取流程变量的方式给任务赋值执行人
         *   2、可以操作数据库
         *      方法一：（必须在web环境） WebApplicationContext ac = WebApplicationContextUtils
         *       	.getWebApplicationContext(ServletActionContext.getServletContext());
                    xxxxService xxxxService = (xxxxService) ac.getBean("xxxxService");
                方法二：通过JDBC来进行数据库操作
         */
        //动态分配（这里是从上一节点中的tack变量的map中获取，只有流程没有结束，所有的变量都是可以获取）
        /*String value = (String)delegateTask.getVariable("aaa");
        delegateTask.setAssignee(value);*/
        //静态分配(用于确定该执行人就只有一种情况，是一种固定的)
        delegateTask.setAssignee("我是班主任");
    }
}
