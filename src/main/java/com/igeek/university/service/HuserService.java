package com.igeek.university.service;

import com.igeek.university.entity.Huser;

import java.util.List;

public interface HuserService {
    Huser registerUser(Huser user);
    Huser loginUser(String username, String password);
    Huser getUserById(int userId);
    List<Huser> getAllUsers(); // 新增获取所有用户信息的方法
    void deleteUser(int userId); // 新增删除用户的方法
}