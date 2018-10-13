package com.hnu.scw.dao.impl;

import com.hnu.scw.dao.LeaveBillDao;
import com.hnu.scw.model.LeaveBill;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author scw
 * @create 2018-01-26 12:30
 * @desc 请假管理的数据库管理层
 **/
@Repository
public class LeaveBillDaoImpl extends BaseDaoImpl<LeaveBill> implements LeaveBillDao{

    @Override
    public List<LeaveBill> findListLeaveBill(LeaveBill leaveBill) {
        String hql = "FROM LeaveBill n where 1 = 1";
        StringBuilder sb = new StringBuilder(hql);
        List<Object> parames = new ArrayList<Object>();
        if(leaveBill != null){

        }
        return super.findQueryMOdel(sb.toString() , parames.toArray());
    }

    @Override
    public List<LeaveBill> findAllLeaveBill(LeaveBill leaveBill) {
        return findListLeaveBill(null);
    }

    @Override
    public LeaveBill findOneLeaveBillById(Long id) {
        return super.load(id);
    }
}
