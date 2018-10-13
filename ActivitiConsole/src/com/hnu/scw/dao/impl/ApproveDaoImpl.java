package com.hnu.scw.dao.impl;

import com.hnu.scw.dao.ApproveDao;
import com.hnu.scw.model.Approve;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scw
 * @create 2018-01-28 17:00
 * @desc 审批内容信息的Dao实现类
 **/
@Repository
public class ApproveDaoImpl extends BaseDaoImpl<Approve> implements ApproveDao{
    @Override
    public List<Approve> findAllApprove(Approve approve) {
        String hql = "FROM Approve a where 1 = 1";
        StringBuilder sb = new StringBuilder(hql);
        List<Object> parames = new ArrayList<Object>();
        if(approve != null){
            if(approve.getLeaveBill() != null){
                sb.append("and a.leaveBill.id = ?");
                parames.add(approve.getLeaveBill().getId());
            }
        }
        return super.findQueryMOdel(sb.toString() , parames.toArray());
    }
}
