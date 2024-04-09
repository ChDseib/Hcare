package com.igeek.university.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class FallDetection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataID;
    private Long userID;
    private LocalDateTime fallTime;
    private String detectionResult;

    // Getters and setters
    public Long getDataID() {
        return dataID;
    }

    public void setDataID(Long dataID) {
        this.dataID = dataID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public LocalDateTime getFallTime() {
        return fallTime;
    }

    public void setFallTime(LocalDateTime fallTime) {
        this.fallTime = fallTime;
    }

    public String getDetectionResult() {
        return detectionResult;
    }

    public void setDetectionResult(String detectionResult) {
        this.detectionResult = detectionResult;
    }
}
