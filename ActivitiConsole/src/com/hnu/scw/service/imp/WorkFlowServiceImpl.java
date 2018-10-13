package com.hnu.scw.service.imp;

import com.hnu.scw.activiti.utils.ActivitiUtils;
import com.hnu.scw.dao.ApproveDao;
import com.hnu.scw.model.Approve;
import com.hnu.scw.model.Employee;
import com.hnu.scw.model.LeaveBill;
import com.hnu.scw.service.LeaveBillService;
import com.hnu.scw.service.WorkFlowService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author scw
 * @create 2018-01-30 15:04
 * @desc 任务处理操作的service实现类
 **/
@Service
public class WorkFlowServiceImpl implements WorkFlowService{
    @Autowired
    private ApproveDao approveDao;
    @Autowired
    private ActivitiUtils activitiUtils;
    @Autowired
    private LeaveBillService leaveBillService;

    @Override
    public void finishCurrentTask(Approve approve, String taskId , Employee assignUser) {
        //1:获取到当前的审批流程的信息，对应a_leavebill数据表
        String businessKey = activitiUtils.getBusinessKeyByTaskId(taskId);
        LeaveBill oneLeaveBill = leaveBillService.findOneLeaveBillById(Long.parseLong(businessKey));
        //2:获取当前执行任务的信息
        Task currentTask = activitiUtils.getTaskByTaskId(taskId);
        //3:提交完成当前的task任务,并且将当前节点的后续需要审批的人员信息，进行传递
        if(assignUser != null){
            activitiUtils.finishCurrentTaskByTaskId(taskId , assignUser.getId());
        }else{
            activitiUtils.finishCurrentTaskByTaskId(taskId , null);
        }
        //4：根据currentTask当前任务中的流程ID，来获取完成该任务后，该流程后面是否还存在任务
        ProcessInstance processInstance = activitiUtils.getProcessInstanceByTask(currentTask);
        //如果为空，则表示该流程结束
        if(processInstance == null){
            //该流程结束，那么就把对应的审批状态设置为“审批完成”
            oneLeaveBill.setState(2);
            //更新对应的请假信息数据的状态
            leaveBillService.updateLeaveBill(oneLeaveBill);
        }
        //5:添加审批信息到审批表中
        approve.setLeaveBill(oneLeaveBill);
        //添加审批信息数据
        approveDao.save(approve);
    }
}
