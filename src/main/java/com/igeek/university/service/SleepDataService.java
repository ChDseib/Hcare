package com.igeek.university.service;

import com.igeek.university.entity.SleepData;

import java.util.Date;
import java.util.List;

public interface SleepDataService {
    List<SleepData> getAllSleepData();

    List<SleepData> getSleepDataByUserID(Long userID);

    void addSleepData(Long userID, Date startTime, Date endTime, Integer qualityScore);

    void deleteSleepData(Long dataID);
}
