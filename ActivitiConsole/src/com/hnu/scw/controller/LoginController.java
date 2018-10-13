package com.hnu.scw.controller;

import com.hnu.scw.activiti.utils.ActivitiUtils;
import com.hnu.scw.model.Employee;
import com.hnu.scw.service.EmployeeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author scw
 * @create 2018-01-25 10:58
 * @desc 登录controller
 **/
@Controller
public class LoginController {
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping(value = "/login")
    public String login(HttpSession httpSession , Employee employee){
        //根据用户name查找到对应的内容信息，并且放入到session中
        Employee currentEmpyolee = employeeService.findEmpyolee(employee);
        httpSession.setAttribute("currentUser" , currentEmpyolee);
        return "main";
    }

    @RequestMapping(value = "/topshow")
    public String top(){
        return "top";
    }

    @RequestMapping(value = "/leftshow")
    public String left(){
        return "left";
    }

    @RequestMapping(value = "/welcomeshow")
    public String welcome(){
        return "welcome";
    }

}
