package com.hnu.scw.service;

import com.hnu.scw.model.Approve;

import java.util.List;

/**
 * 请假审批的service
 */
public interface ApproveService {
    /**
     * 获取到所有的请假审批的内容信息
     * @param approve
     * @return
     */
    List<Approve> findAllApprove(Approve approve);

    /**
     * 添加一条申请审批的数据
     * @param approve
     */
    void addCurrentApprove(Approve approve);
}
