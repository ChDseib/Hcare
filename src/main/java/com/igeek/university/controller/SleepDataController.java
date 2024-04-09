package com.igeek.university.controller;

import com.igeek.university.entity.SleepData;
import com.igeek.university.service.SleepDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sleepdata")
public class SleepDataController {

    @Autowired
    private SleepDataService sleepDataService;

    @PostMapping
    public ResponseEntity<String> addSleepData(@RequestBody SleepData request) {
        sleepDataService.addSleepData(request.getUserID(), request.getStartTime(), request.getEndTime(), request.getQualityScore());
        return ResponseEntity.ok("{\"message\": \"睡眠数据添加成功\"}");
    }


    @GetMapping("/{userID}")
    public List<SleepData> getSleepDataByUserID(@PathVariable Long userID) {
        return sleepDataService.getSleepDataByUserID(userID);
    }

    @GetMapping("/all")
    public List<SleepData> getAllSleepData() {
        return sleepDataService.getAllSleepData();
    }

    @DeleteMapping("/{dataID}")
    public ResponseEntity<String> deleteSleepData(@PathVariable Long dataID) {
        sleepDataService.deleteSleepData(dataID);
        return ResponseEntity.ok("{\"message\": \"睡眠数据删除成功\"}");
    }
}
