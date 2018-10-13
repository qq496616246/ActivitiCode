package com.hnu.scw.controller;

import com.hnu.scw.activiti.utils.ActivitiUtils;
import com.hnu.scw.model.Approve;
import com.hnu.scw.model.Employee;
import com.hnu.scw.model.LeaveBill;
import com.hnu.scw.service.ApproveService;
import com.hnu.scw.service.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author scw
 * @create 2018-01-26 12:20
 * @desc 请假管理的controller控制层
 **/
@Controller
public class LeaveBillController {
    @Autowired
    private LeaveBillService leaveBillService;
    @Autowired
    private ActivitiUtils activitiUtils;
    @Autowired
    private ApproveService approveService;
    /**
     * 显示请假列表
     * @return
     */
    @RequestMapping(value = "/showLeaveBills")
    public String showLeaveBillList(Map<String , Object> map){
        //获取到请假列表中的所有数据信息
        List<LeaveBill> allLeaveBill = leaveBillService.findAllLeaveBill(null);
        map.put("leavebills" , allLeaveBill);
        return "leaveBill/list";
    }

    /**
     * 删除请假申请
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteleavebill")
    public String deleteLeaveBillByLeavebillId(String id){
        //首先获取到对应id的请假内容
        LeaveBill oneLeaveBillById = leaveBillService.findOneLeaveBillById(Long.parseLong(id));
        leaveBillService.deleteLeaveBillById(oneLeaveBillById);
        return "redirect:/showLeaveBills";
    }


    /**
     * 跳转到添加请假条的页面
     * @return
     */
    @RequestMapping(value = "/toaddleavebill")
    public String toAddLeaveBillUI(){
        return "leaveBill/input";
    }

    /**
     * 进行添加请假条的操作
     * @return
     */
    @RequestMapping(value = "/add")
    public String addLeaveBill(HttpSession httpSession , LeaveBill leaveBill){
        Employee employee = (Employee) httpSession.getAttribute("currentUser");
        leaveBill.setEmployee(employee);
        leaveBillService.addLeaveBill(leaveBill);
        return "redirect:/showLeaveBills";
    }

    /**
     * 进行流程实例的开启
     * @param id  对应的请假内容数据表中的id，也就是activiti中的busness_key
     * @param session
     * @return
     */
    @RequestMapping(value = "/tosubmitui")
    public String toWorkFlowUI(String id ,HttpSession session){
        //1：得到当前用户
        Employee employee = (Employee) session.getAttribute("currentUser");
        //2：开启流程实例
        activitiUtils.startProceesInstance(Long.parseLong(id) , employee.getId().toString());
        //3:将请假状态，变成申请中
        LeaveBill currentLeaveBill = leaveBillService.findOneLeaveBillById(Long.parseLong(id));
        currentLeaveBill.setState(1);
        leaveBillService.updateLeaveBill(currentLeaveBill);
        //4:返回到页面
        return "redirect:/showLeaveBills";
    }


    /**
     * 查询当前流程审批的记录
     * @param leaveBill  主要是用于接受需要查看的请假流程的id
     * @return
     */
    @RequestMapping(value = "/querycurrentrecord")
    public String queryCurrentLeaveBillInfo(LeaveBill leaveBill , Map<String , Object> map){
        Approve approve = new Approve();
        approve.setLeaveBill(leaveBill);
        List<Approve> allApprove = approveService.findAllApprove(approve);
        map.put("approveinfo" , allApprove);
        return "leaveBill/leavebill_current_list";
    }

    /**
     * 跳转到更新当前请假信息的页面
     * @param leaveBill
     * @return
     */
    @RequestMapping(value = "/tocurrentleavebill")
    public String toUpdateLeaveBillUI(LeaveBill leaveBill , Map<String , Object>map){
        LeaveBill oneLeaveBillById = leaveBillService.findOneLeaveBillById(leaveBill.getId());
        map.put("currentleavebill" , oneLeaveBillById);
        return "leaveBill/leavebill_update";
    }

    /**
     * 更新当前的请假信息
     * @param leaveBill
     * @return
     */
    @RequestMapping(value = "/updateleavebill")
    public String updateCurrentLeaveBillInfo(LeaveBill leaveBill , HttpSession session){
        Employee currentUser = (Employee) session.getAttribute("currentUser");
        leaveBill.setEmployee(currentUser);
        leaveBillService.updateLeaveBill(leaveBill);
        return "redirect:/showLeaveBills";
    }
}
