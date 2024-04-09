package com.igeek.university.service;

import com.igeek.university.entity.HealthData;

import java.util.List;

public interface HealthDataService {
    HealthData addHealthData(HealthData healthData);
    List<HealthData> getHealthDataByUserId(Long userId);
    List<HealthData> getAllHealthData();
    boolean deleteHealthData(Long dataId);
}
