package com.hnu.scw.controller;

import com.hnu.scw.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

/**
 * @author scw
 * @create 2018-01-25 10:58
 * @desc 登录controller
 **/
@Controller
public class LoginController {
    @RequestMapping(name = "/login")
    public String login(Employee employee , HttpSession session){
        /*ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        request.getSession();*/
        session.setAttribute("user" , employee);
        return "hello";
    }

}
