package com.igeek.university.service;

import com.igeek.university.entity.FallDetection;

import java.util.List;

public interface FallDetectionService {
    void addFallDetection(Long userID, String fallTime, String detectionResult);
    List<FallDetection> getFallDetectionByUserID(Long userID);
    void deleteFallDetection(Long dataID);
    List<FallDetection> getAllFallDetection();
}
