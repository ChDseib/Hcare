package com.igeek.university.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HeartRateAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dataId;

    private int userId;

    private LocalDateTime alertTime;

    private String alertType;

    // Getters and Setters

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(LocalDateTime alertTime) {
        this.alertTime = alertTime;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }
}
