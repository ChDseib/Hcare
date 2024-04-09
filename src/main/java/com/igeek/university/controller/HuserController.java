package com.igeek.university.controller;

import com.igeek.university.entity.Huser;
import com.igeek.university.service.HuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/huser")
public class HuserController {

    @Autowired
    private HuserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody Huser user) {
        Huser registeredUser = userService.registerUser(user);
        if (registeredUser != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "用户注册成功");
            response.put("UserID", registeredUser.getUserID());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "用户注册失败");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @GetMapping("/list")
    public ResponseEntity<Object> getAllUsers() {
        List<Huser> userList = userService.getAllUsers();
        if (!userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "没有找到用户信息");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Huser loggedInUser = userService.loginUser(username, password);
        if (loggedInUser != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "登录成功");
            response.put("UserID", loggedInUser.getUserID());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "用户名或密码错误");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @DeleteMapping("/del/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable int userId) {
        Huser userToDelete = userService.getUserById(userId);
        if (userToDelete != null) {
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body("用户已成功删除");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("未找到该用户");
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable int userId) {
        Huser user = userService.getUserById(userId);
        if (user != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("UserID", user.getUserID());
            response.put("Username", user.getUsername());
            response.put("Name", user.getName());
            response.put("Age", user.getAge());
            response.put("Gender", user.getGender());
            response.put("Phone", user.getPhone());
            response.put("Role", user.getRole());
            response.put("BindingID", user.getBindingID());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "用户不存在");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
