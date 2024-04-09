package com.igeek.university.service;

import com.igeek.university.entity.HeartRateAlert;

import java.util.List;

public interface HeartRateAlertService {
    void addHeartRateAlert(int userId, String alertTime, String alertType);
    List<HeartRateAlert> getHeartRateAlertsByUserId(int userId);
    List<HeartRateAlert> getAllHeartRateAlerts();
    void deleteHeartRateAlert(int dataId);
}
