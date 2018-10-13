package com.hnu.scw.dao;

import com.hnu.scw.model.Approve;

import java.util.List;

/**
 * 审批内容的信息
 */
public interface ApproveDao extends BaseDao<Approve>{
    /**
     * 获取所有的审批内容的信息
     * @param approve
     * @return
     */
    List<Approve> findAllApprove(Approve approve);
}
