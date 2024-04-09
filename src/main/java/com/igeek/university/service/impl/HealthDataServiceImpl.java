package com.igeek.university.service.impl;

import com.igeek.university.dao.HealthDataDao;
import com.igeek.university.entity.HealthData;
import com.igeek.university.service.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthDataServiceImpl implements HealthDataService {

    @Autowired
    private HealthDataDao healthDataDao;

    @Override
    public HealthData addHealthData(HealthData healthData) {
        return healthDataDao.save(healthData);
    }

    @Override
    public List<HealthData> getHealthDataByUserId(Long userId) {
        return healthDataDao.findByUserId(userId);
    }

    @Override
    public List<HealthData> getAllHealthData() {
        return healthDataDao.findAll();
    }

    @Override
    public boolean deleteHealthData(Long dataId) {
        if (healthDataDao.existsById(dataId)) {
            healthDataDao.deleteById(dataId);
            return true;
        }
        return false;
    }
}
