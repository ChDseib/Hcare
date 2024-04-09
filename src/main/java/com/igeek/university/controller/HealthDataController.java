package com.igeek.university.controller;

import com.igeek.university.entity.HealthData;
import com.igeek.university.service.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/healthdata")
public class HealthDataController {

    @Autowired
    private HealthDataService healthDataService;

    @PostMapping("/add")
    public ResponseEntity<?> addHealthData(@RequestBody HealthData request) {
        // 将传入的时间戳字符串转换为 LocalDateTime 对象
        LocalDateTime timestamp = LocalDateTime.parse(request.getTimestamp(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // 创建 HealthData 对象并设置属性
        HealthData healthData = new HealthData();
        healthData.setUserId(request.getUserId());
        healthData.setSteps(request.getSteps());
        healthData.setHeartRate(request.getHeartRate());
        healthData.setBloodOxygen(request.getBloodOxygen());
        healthData.setTimestamp(String.valueOf(timestamp));

        // 执行其他操作（将HealthData保存到数据库等）
        healthDataService.addHealthData(healthData);

        // 返回成功响应
        return ResponseEntity.ok().body("{\"message\": \"健康数据添加成功\"}");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<HealthData>> getHealthDataByUserId(@PathVariable Long userId) {
        List<HealthData> healthDataList = healthDataService.getHealthDataByUserId(userId);
        return ResponseEntity.ok().body(healthDataList);
    }

    @GetMapping("/list")
    public ResponseEntity<List<HealthData>> getAllHealthData() {
        List<HealthData> healthDataList = healthDataService.getAllHealthData();
        return ResponseEntity.ok().body(healthDataList);
    }

    @DeleteMapping("/del/{dataId}")
    public ResponseEntity<?> deleteHealthData(@PathVariable Long dataId) {
        boolean deleted = healthDataService.deleteHealthData(dataId);
        if (deleted) {
            return ResponseEntity.ok().body("{\"message\": \"健康数据删除成功\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
