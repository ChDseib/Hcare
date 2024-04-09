package com.igeek.university.controller;

import com.igeek.university.entity.FallDetection;
import com.igeek.university.service.FallDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/falldetection")
public class FallDetectionController {

    @Autowired
    private FallDetectionService fallDetectionService;

    @PostMapping("add")
    public ResponseEntity<?> addFallDetection(@RequestBody FallDetection request) {
        fallDetectionService.addFallDetection(request.getUserID(), String.valueOf(request.getFallTime()), request.getDetectionResult());
        return ResponseEntity.ok().body("{\"message\": \"摔倒检测数据添加成功\"}");
    }

    @GetMapping("/{userID}")
    public ResponseEntity<?> getFallDetectionByUserID(@PathVariable Long userID) {
        List<FallDetection> fallDetectionList = fallDetectionService.getFallDetectionByUserID(userID);
        return ResponseEntity.ok().body(fallDetectionList);    }

    @DeleteMapping("/{dataID}")
    public ResponseEntity<?> deleteFallDetection(@PathVariable Long dataID) {
        fallDetectionService.deleteFallDetection(dataID);
        return ResponseEntity.ok().body("{\"message\": \"摔倒检测数据删除成功\"}");
    }

    @GetMapping
    public ResponseEntity<?> getAllFallDetection() {
        List<FallDetection> fallDetectionList = fallDetectionService.getAllFallDetection();
        return ResponseEntity.ok().body(fallDetectionList);
    }
}
