package com.igeek.university.service.impl;

import com.igeek.university.dao.FallDetectionRepository;
import com.igeek.university.entity.FallDetection;
import com.igeek.university.service.FallDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FallDetectionServiceImpl implements FallDetectionService {

    @Autowired
    private FallDetectionRepository fallDetectionRepository;

    @Override
    public void addFallDetection(Long userID, String fallTime, String detectionResult) {
        FallDetection fallDetection = new FallDetection();
        fallDetection.setUserID(userID);
        fallDetection.setFallTime(LocalDateTime.parse(fallTime));
        fallDetection.setDetectionResult(detectionResult);
        fallDetectionRepository.save(fallDetection);
    }

    @Override
    public List<FallDetection> getFallDetectionByUserID(Long userID) {
        return fallDetectionRepository.findByUserID(userID);
    }

    @Override
    public void deleteFallDetection(Long dataID) {
        fallDetectionRepository.deleteById(dataID);
    }

    @Override
    public List<FallDetection> getAllFallDetection() {
        return fallDetectionRepository.findAll();
    }
}
