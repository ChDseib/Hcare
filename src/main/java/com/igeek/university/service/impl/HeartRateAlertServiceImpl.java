package com.igeek.university.service.impl;

import com.igeek.university.dao.HeartRateAlertDAO;
import com.igeek.university.entity.HeartRateAlert;
import com.igeek.university.service.HeartRateAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HeartRateAlertServiceImpl implements HeartRateAlertService {

    @Autowired
    private HeartRateAlertDAO heartRateAlertDAO;

    @Override
    public void addHeartRateAlert(int userId, String alertTime, String alertType) {
        HeartRateAlert heartRateAlert = new HeartRateAlert();
        heartRateAlert.setUserId(userId);
        heartRateAlert.setAlertTime(LocalDateTime.parse(alertTime));
        heartRateAlert.setAlertType(alertType);
        heartRateAlertDAO.save(heartRateAlert);
    }

    @Override
    public List<HeartRateAlert> getHeartRateAlertsByUserId(int userId) {
        return heartRateAlertDAO.findAllByUserId(userId);
    }

    @Override
    public List<HeartRateAlert> getAllHeartRateAlerts() {
        return heartRateAlertDAO.findAll();
    }

    @Override
    public void deleteHeartRateAlert(int dataId) {
        heartRateAlertDAO.deleteById(dataId);
    }
}
