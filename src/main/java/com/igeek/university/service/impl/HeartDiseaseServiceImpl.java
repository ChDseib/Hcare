package com.igeek.university.service.impl;

import com.igeek.university.dao.IHeartDiseaseDao;
import com.igeek.university.entity.HeartDisease;
import com.igeek.university.service.IHeartDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeartDiseaseServiceImpl implements IHeartDiseaseService {
    @Autowired
    private IHeartDiseaseDao heartDiseaseDao;

    @Override
    public HeartDisease save(HeartDisease heartDisease) {
        return heartDiseaseDao.save(heartDisease);
    }

    @Override
    public HeartDisease findById(Integer id) {
        return heartDiseaseDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        heartDiseaseDao.deleteById(id);
    }
    @Override
    public List<HeartDisease> findAll() {
        return heartDiseaseDao.findAll();
    }
}