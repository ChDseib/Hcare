package com.igeek.university.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HeartDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer age;
    private Integer gender;
    private Integer chestPainType;
    private Integer restingBloodPressure;
    private Integer cholesterol;
    private Integer fastingBloodSugar;
    private Integer restingECG;
    private Integer maxHeartRate;
    private Integer exerciseInducedAngina;
    private Float stDepression;

    // Add getters and setters for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getChestPainType() {
        return chestPainType;
    }

    public void setChestPainType(Integer chestPainType) {
        this.chestPainType = chestPainType;
    }

    public Integer getRestingBloodPressure() {
        return restingBloodPressure;
    }

    public void setRestingBloodPressure(Integer restingBloodPressure) {
        this.restingBloodPressure = restingBloodPressure;
    }

    public Integer getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Integer cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Integer getFastingBloodSugar() {
        return fastingBloodSugar;
    }

    public void setFastingBloodSugar(Integer fastingBloodSugar) {
        this.fastingBloodSugar = fastingBloodSugar;
    }

    public Integer getRestingECG() {
        return restingECG;
    }

    public void setRestingECG(Integer restingECG) {
        this.restingECG = restingECG;
    }

    public Integer getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(Integer maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public Integer getExerciseInducedAngina() {
        return exerciseInducedAngina;
    }

    public void setExerciseInducedAngina(Integer exerciseInducedAngina) {
        this.exerciseInducedAngina = exerciseInducedAngina;
    }

    public Float getStDepression() {
        return stDepression;
    }

    public void setStDepression(Float stDepression) {
        this.stDepression = stDepression;
    }
}