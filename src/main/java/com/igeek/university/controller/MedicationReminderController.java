package com.igeek.university.controller;

import com.igeek.university.entity.MedicationReminder;
import com.igeek.university.service.MedicationReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medicationreminder")
public class MedicationReminderController {

    @Autowired
    private MedicationReminderService medicationReminderService;

    @PostMapping("/add")
    public ResponseEntity<?> addReminder(@RequestBody MedicationReminder reminder) {
        MedicationReminder addedReminder = medicationReminderService.addReminder(reminder);
        if (addedReminder != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "用药提醒添加成功");
            return ResponseEntity.ok().body(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "用药提醒添加失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{userID}")
    public ResponseEntity<List<MedicationReminder>> getRemindersByUserID(@PathVariable Long userID) {
        List<MedicationReminder> reminders = medicationReminderService.getRemindersByUserID(userID);
        return ResponseEntity.ok().body(reminders);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MedicationReminder>> getAllReminders() {
        List<MedicationReminder> reminders = medicationReminderService.getAllReminders();
        return ResponseEntity.ok().body(reminders);
    }


    @DeleteMapping("/{reminderID}")
    public ResponseEntity<?> deleteReminder(@PathVariable Long reminderID) {
        medicationReminderService.deleteReminder(reminderID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "用药提醒删除成功");
        return ResponseEntity.ok().body(response);
    }
}
