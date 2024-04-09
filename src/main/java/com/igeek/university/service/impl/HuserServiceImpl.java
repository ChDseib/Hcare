package com.igeek.university.service.impl;

import com.igeek.university.dao.HuserRepository;
import com.igeek.university.entity.Huser;
import com.igeek.university.service.HuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HuserServiceImpl implements HuserService {

    @Autowired
    private HuserRepository userRepository;

    @Override
    public Huser registerUser(Huser user) {
        // 实现用户注册逻辑
        return userRepository.save(user);
    }

    @Override
    public Huser loginUser(String username, String password) {
        // 实现用户登录逻辑
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Huser getUserById(int userId) {
        // 实现根据ID获取用户信息逻辑
        Optional<Huser> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }
    @Override
    public List<Huser> getAllUsers() {
        // 实现获取所有用户信息逻辑
        return userRepository.findAll();
    }
    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
