package com.hnu.scw.service;

import com.hnu.scw.model.Approve;
import com.hnu.scw.model.Employee;

/**
 * 进行任务处理的service
 */
public interface WorkFlowService {
    /**
     * 对当前的任务节点进行提交完成的处理
     * @param approve 审批的内容信息
     * @param taskId  当前审批的任务ID
     * @param assignUser 当前任务的下一个审批任务的具体审批人的信息，如果后面没有审批人，那么就是为null
     */
    void finishCurrentTask(Approve approve, String taskId , Employee assignUser);
}
