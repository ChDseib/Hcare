package com.igeek.university.service.impl;

import com.igeek.university.dao.ILoginDao;
import com.igeek.university.entity.Result;
import com.igeek.university.entity.University;
import com.igeek.university.entity.User;
import com.igeek.university.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    ILoginDao loginDao;


    @Override
    public String login(User user) {
        List<User> list = new ArrayList<>();
        String s="false";
        if(user.getUname()!=null&&user.getPwd()!=null){
            list = loginDao.query(user.getUname(),user.getPwd());
            if (list.isEmpty()){
                System.out.println("登录失败");
                s="false";
            }else {
                System.out.println("登录成功");
                s="true";
            }
        }
        return s;
    }

    @Override
    public void add(User user) throws Exception {
        loginDao.save(user);
    }
}
