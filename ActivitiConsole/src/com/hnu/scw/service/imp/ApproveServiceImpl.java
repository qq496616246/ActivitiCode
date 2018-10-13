package com.hnu.scw.service.imp;

import com.hnu.scw.dao.ApproveDao;
import com.hnu.scw.model.Approve;
import com.hnu.scw.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author scw
 * @create 2018-01-28 17:02
 * @desc 请假审批的操作service
 **/
@Service
public class ApproveServiceImpl implements ApproveService{
    @Autowired
    private ApproveDao approveDao;

    @Override
    public List<Approve> findAllApprove(Approve approve) {
        return approveDao.findAllApprove(approve);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCurrentApprove(Approve approve) {
        approveDao.save(approve);
    }
}
