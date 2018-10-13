package com.hnu.scw.service.imp;

import com.hnu.scw.activiti.utils.ActivitiUtils;
import com.hnu.scw.dao.LeaveBillDao;
import com.hnu.scw.model.LeaveBill;
import com.hnu.scw.service.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author scw
 * @create 2018-01-27 17:06
 * @desc 对于请假单信息表的处理service实现类
 **/
@Service
public class LeaveBillServiceImpl implements LeaveBillService{
    @Autowired
    private LeaveBillDao leaveBillDao;

    @Override
    public List<LeaveBill> findAllLeaveBill(LeaveBill leaveBill) {
        return leaveBillDao.findAllLeaveBill(null);
    }

    @Override
    public void addLeaveBill(LeaveBill leaveBill) {
        leaveBill.setLeaveDate(new Date());
        leaveBill.setState(0);
        leaveBillDao.save(leaveBill);
    }

    @Override
    public LeaveBill findOneLeaveBillById(Long id) {
        LeaveBill oneLeaveBill = leaveBillDao.findOneLeaveBillById(id);
        return oneLeaveBill;
    }

    @Override
    public void submitWorkFlow(Long id) {
        LeaveBill oneLeaveBill = leaveBillDao.findOneLeaveBillById(id);
        //表示是开始进行审核
        oneLeaveBill.setState(1);
    }

    @Override
    public void updateLeaveBill(LeaveBill leaveBill) {
        leaveBillDao.update(leaveBill);
    }

    @Override
    public void deleteLeaveBillById(LeaveBill leaveBill) {
        leaveBillDao.delete(leaveBill);
    }
}
