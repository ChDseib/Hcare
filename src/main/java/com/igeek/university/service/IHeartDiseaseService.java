package com.igeek.university.service;

import com.igeek.university.entity.HeartDisease;

import java.util.List;

public interface IHeartDiseaseService {
    // Define methods for business operations on heartdisease
    HeartDisease save(HeartDisease heartDisease);
    List<HeartDisease> findAll();
    HeartDisease findById(Integer id);
    void deleteById(Integer id);


}