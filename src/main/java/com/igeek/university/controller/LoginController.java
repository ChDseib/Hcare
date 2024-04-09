package com.igeek.university.controller;

import com.igeek.university.entity.University;
import com.igeek.university.entity.User;
import com.igeek.university.entity.Result;
import com.igeek.university.entity.WriteItem;
import com.igeek.university.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    ILoginService loginService;



    @PostMapping("/login")
    public String login(User user) {
            if(user.getUname()!=null&&user.getPwd()!=null){
                return loginService.login(user);
            }
        return "false";
    }

    @PostMapping("/add")
    public String add(User user){
        System.out.println("--注册---"+user);
        try {
            loginService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "注册失败！";
        }
        return "注册成功！";
    }

}
