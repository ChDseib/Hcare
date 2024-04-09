package com.igeek.university.service.impl;

import com.igeek.university.dao.IUniversityDao;
import com.igeek.university.entity.Result;
import com.igeek.university.entity.University;
import com.igeek.university.service.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UniversityServiceImpl implements IUniversityService {
    @Autowired
    IUniversityDao universityDao;

    @Override
    public Result showList(int page) {
        Result result = new Result();
        result.setData(universityDao.findAll(
                PageRequest.of(page -1,10)).getContent());
        result.setCount(universityDao.count());
        return result;
    }

    @Override
    public Result showList(int page, University u) {
        Result result = new Result();
        List<University> list = new ArrayList<>();
        int count=0;
        if(!u.getName().equals("")){
            list = universityDao.findByNameLikeAndAddressAndRemarkAndFeature(u.getName(),u.getAddress(),u.getRemark(),u.getFeature(),PageRequest.of(page -1,10));
            count = universityDao.countByNameLikeAndAddressAndRemarkAndFeature(u.getName(),u.getAddress(),u.getRemark(),u.getFeature());
        }else{
            list = universityDao.findByAddressAndRemarkAndFeature(u.getAddress(),u.getRemark(),u.getFeature(),PageRequest.of(page -1,10)) ;
            count = universityDao.countByAddressAndRemarkAndFeature(u.getAddress(),u.getRemark(),u.getFeature());
        }
        result.setData(list);
        result.setCount(count);
        return result;
    }

    @Override
    public void addUniversity(University university) throws Exception {
        universityDao.save(university);
    }

    @Override
    public void delUniversity(University university) throws Exception {
        universityDao.deleteById(university.getUid());
    }

    @Override
    public void editUniversity(University university) throws Exception {
        universityDao.save(university);
    }
}
