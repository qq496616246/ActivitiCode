package com.hnu.scw.service;

import com.hnu.scw.model.LeaveBill;

import java.util.List;

/**
 * 关于请假单表的处理接口
 */
public interface LeaveBillService {
    /**
     * 获取所有的请假单的信息
     * @param leaveBill  查询的条件内容，当查询所有的时候，这个条件为null即可
     * @return
     */
    public List<LeaveBill> findAllLeaveBill(LeaveBill leaveBill);

    /**
     * 添加请假条数据
     * @param leaveBill
     */
    public void addLeaveBill(LeaveBill leaveBill);

    /**
     * 通过ID，获取对应的请假条信息
     * @param id
     * @return
     */
    public LeaveBill findOneLeaveBillById(Long id);

    /**
     * 提交请假流程的任务
     * @param id
     */
    public void submitWorkFlow(Long id);

    /**
     * 更新请假信息
     * @param leaveBill
     */
    public void updateLeaveBill(LeaveBill leaveBill);

    /**
     * 根据ID进行删除请假信息
     * @param leaveBill
     */
    public void deleteLeaveBillById(LeaveBill leaveBill);

}
