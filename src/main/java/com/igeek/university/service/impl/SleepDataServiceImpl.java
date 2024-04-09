package com.igeek.university.service.impl;

import com.igeek.university.dao.SleepDataRepository;
import com.igeek.university.entity.SleepData;
import com.igeek.university.service.SleepDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SleepDataServiceImpl implements SleepDataService {

    @Autowired
    private SleepDataRepository sleepDataRepository;

    @Override
    public List<SleepData> getAllSleepData() {
        return sleepDataRepository.findAll();
    }

    @Override
    public List<SleepData> getSleepDataByUserID(Long userID) {
        // Implement logic to retrieve sleep data by user ID
        return sleepDataRepository.findByUserID(userID);
    }

    @Override
    public void addSleepData(Long userId, Date startTime, Date endTime, Integer qualityScore) {
        SleepData sleepData = new SleepData();
        sleepData.setUserID(userId);
        sleepData.setStartTime(startTime);
        sleepData.setEndTime(endTime);
        sleepData.setQualityScore(qualityScore);
        sleepDataRepository.save(sleepData);
    }

    @Override
    public void deleteSleepData(Long dataID) {
        sleepDataRepository.deleteById(dataID);
    }
}
