package com.hnu.scw.dao;

import com.hnu.scw.model.LeaveBill;
import java.util.List;

/**
 * 对于请假表的Dao管理
 */
public interface LeaveBillDao extends BaseDao<LeaveBill>{

    /**
     * 获取到相应内容的请假数据库中的表信息
     * @param leaveBill
     * @return
     */
    public List<LeaveBill> findListLeaveBill(LeaveBill leaveBill);

    /**
     * 查询所有的请假单信息
     * @param leaveBill
     * @return
     */
    public List<LeaveBill> findAllLeaveBill(LeaveBill leaveBill);

    /**
     * 根据请假ID，获取到对应的请假信息
     * @param id
     * @return
     */
    public LeaveBill findOneLeaveBillById(Long id);
}
