package com.igeek.university.controller;

import com.igeek.university.entity.HeartRateAlert;
import com.igeek.university.service.HeartRateAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heartratealert")
public class HeartRateAlertController {

    @Autowired
    private HeartRateAlertService heartRateAlertService;
    @PostMapping("/add")
    public ResponseEntity<String> addHeartRateAlert(@RequestBody HeartRateAlert request) {
        heartRateAlertService.addHeartRateAlert(request.getUserId(), String.valueOf(request.getAlertTime()), request.getAlertType());
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"心率异常提醒数据添加成功\"}");
    }

    @GetMapping("/{UserID}")
    public ResponseEntity<List<HeartRateAlert>> getHeartRateAlertsByUserId(@PathVariable int UserID) {
        List<HeartRateAlert> heartRateAlerts = heartRateAlertService.getHeartRateAlertsByUserId(UserID);
        return ResponseEntity.ok().body(heartRateAlerts);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HeartRateAlert>> getAllHeartRateAlerts() {
        List<HeartRateAlert> heartRateAlerts = heartRateAlertService.getAllHeartRateAlerts();
        return ResponseEntity.ok().body(heartRateAlerts);
    }

    @DeleteMapping("/{DataID}")
    public ResponseEntity<String> deleteHeartRateAlert(@PathVariable int DataID) {
        heartRateAlertService.deleteHeartRateAlert(DataID);
        return ResponseEntity.ok().body("{\"message\": \"心率异常数据删除成功\"}");
    }
}
