package com.hnu.scw.controller;
import com.hnu.scw.activiti.utils.ActivitiUtils;
import com.hnu.scw.model.Approve;
import com.hnu.scw.model.Employee;
import com.hnu.scw.model.LeaveBill;
import com.hnu.scw.service.ApproveService;
import com.hnu.scw.service.LeaveBillService;
import com.hnu.scw.service.WorkFlowService;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author scw
 * @create 2018-01-25 13:44
 * @desc  关于流程处理的controller
 **/
@Controller
public class WorkFlowController {
    @Resource(name = "activitiUtils")
    private ActivitiUtils activitiUtils;
    @Autowired
    private LeaveBillService leaveBillService;
    @Autowired
    private ApproveService approveService;
    @Autowired
    private WorkFlowService workFlowService;

    /**
     * 显示部署流程的页面，并且同时获取到已经存在的流程的数据内容
     * @param map
     * @return
     */
    @RequestMapping(value = "/workflowlist")
    public String workFlowList(Map<String , Object> map){
        //获取所有的流程实例
        List<ProcessDefinition> allProcessInstance = activitiUtils.getAllProcessInstance();
        //获取所有的部署内容
        List<Deployment> allDeplyoment = activitiUtils.getAllDeplyoment();
        map.put("process" , allProcessInstance);
        map.put("deplyoments" , allDeplyoment);
        return "workflow/workflow";
    }

    /**
     * 通过部署流程的ID，来进行删除部署内容
     * @param deploymentId
     * @return
     */
    @RequestMapping(value = "/deletedeplyoment")
    public String deleteDeploymentById(String deploymentId){
        activitiUtils.deleteDeplyomentByPID(deploymentId);
        return "redirect:/workflowlist";
    }

    /**
     * 通过流程的ID，来进行查看流程图
     * @param pdid
     * @return
     */
    @RequestMapping(value = "/lookprocess")
    public String lookProcessByProcessId(String pdid , HttpServletResponse response) throws IOException {
        //1：得到图片的输入流内容
        InputStream inputStream = activitiUtils.lookProcessPicture(pdid);
        //2:得到输出流
        ServletOutputStream os = response.getOutputStream();
        //3:进行输出
        int read;
        byte[] buffer = new byte[4*1024];
        while((read = inputStream.read(buffer)) > 0){
            os.write(buffer, 0, read);
        }
        return null;
    }

    /**
     * 部署新的流程
     * @return
     */
    @RequestMapping(value = "/deplyonewprocess")
    public String deplyoNewProcessByZipFile(String fileName , @RequestParam("file")  MultipartFile file) throws IOException {
        activitiUtils.deplyoProcessByInputSterm(file.getInputStream() , fileName);

        return "redirect:/workflowlist";
    }

    /**
     *显示当前登陆人的所有任务列表
     * @return
     */
    @RequestMapping(value = "/showtasklist")
    public String showTaskListUI(HttpSession session , Map<String , Object> map){
        //1:得到当前登陆人信息
       Employee employee = (Employee) session.getAttribute("currentUser");
       //2:查询当前登录人的任务信息
        List<Task> tasks = activitiUtils.queryCurretUserTaskByAssignerr(employee.getId().toString());
       //3:放入到map中
        map.put("user" , employee);
        map.put("tasks" , tasks);
        return "workflow/task";
    }

    /**
     * 显示当前流程处理到的节点图
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/showcurrentview")
    public String showCurrentTaskView(String taskId  , Map<String , Object> map){
        //1:根据taskid获取到depyomentID，用于获取当前的整个流程图
        ProcessInstance instanceByTask = activitiUtils.getProcessInstanceByTask(activitiUtils.getTaskByTaskId(taskId));
        String pdId = instanceByTask.getProcessDefinitionId();
        map.put("deplyomentId" , pdId);
        //2:获取当前的节点实例，便于在jsp绘制当前节点信息
        ActivityImpl implByTaskId = activitiUtils.getActivityImplByTaskId(taskId);
        map.put("currentTask" , implByTaskId);
        return "workflow/image";
    }


    /**
     * 跳转到当前任务的UI页面
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/totask")
    public String toTaskUI(String taskId , Map<String , Object> map){
        //1:根据taskId获取到Buseniess_key，为了获取到当前任务的内容
        String businessKey = activitiUtils.getBusinessKeyByTaskId(taskId);
        //2:根据business_key获取到对应的请假信息内容
        LeaveBill oneLeaveBill = leaveBillService.findOneLeaveBillById(Long.parseLong(businessKey));
        //3：获取到当前流程节点的出口内容
        ActivityImpl activityImpl = activitiUtils.getActivityImplByTaskId(taskId);
        List<PvmTransition> currentActivitiImplPvm = activitiUtils.getCurrentActivitiImplPvm(activityImpl);
        //4:获取到对于当前任务的所有审批内容
        Approve approve = new Approve();
        approve.setLeaveBill(oneLeaveBill);
        List<Approve> allApprove = approveService.findAllApprove(approve);
        map.put("leavebill" , oneLeaveBill);
        map.put("tackId" , taskId);
        map.put("sequenceflow" , currentActivitiImplPvm);
        map.put("approves" , allApprove);
        return "workflow/taskForm";
    }

    /**
     * 完成当然流程任务（这个是非常重要非常重要的）
     * @param taskId 当前任务ID
     * @param approve 封装审批信息对象
     * @param session 为了获取当前审批的人信息
     * @return
     */
    @RequestMapping(value = "/submittask")
    public String finishTaskByTaskId(String taskId , Approve approve , HttpSession session){
        //1:将批注信息添加到请假审核的数据表中a_approver中
        Employee employee = (Employee) session.getAttribute("currentUser");
        approve.setName(employee.getName());
        //设置审批的状态，1表示通过，目前只处理通过的情况，以后如果对不通过有特别需求，那么就再进行相应的开发即可
        approve.setState("1");
        //2：进行相应的任务完成的处理操作
        workFlowService.finishCurrentTask(approve,taskId , employee.getManager());
        return "redirect:/showtasklist";
    }

}
