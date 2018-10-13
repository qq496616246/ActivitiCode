package com.hnu.scw.tasklistener;

import com.hnu.scw.model.Employee;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author scw
 * @create 2018-01-28 17:19
 * @desc 自定义的任务分配人员的监听，因为这个是一个动态分配任务执行人的形式来进行审批处理
 **/
@SuppressWarnings("serial")
public class MyCustomerTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        //1:获取到完成上一个审批task，传送过来的审批人的信息
        Object assigeUser = delegateTask.getVariable("assigeUser");
        //3:分配相应的审批人ID内容，即是对应审批人的主键ID，这样就可以知道下一个审批的人是谁了
        //如果是个null，就表示不需要进行分配对应的审批人了
        if(assigeUser != null){
            delegateTask.setAssignee(assigeUser.toString());
        }
    }
}
