package com.igeek.university.service.impl;

import com.igeek.university.dao.IFirstTypeDao;
import com.igeek.university.entity.FirstType;
import com.igeek.university.entity.Result;
import com.igeek.university.service.IFirstTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FirstTypeServiceImpl implements IFirstTypeService {
    @Autowired
    IFirstTypeDao firstTypeDao;
    @Override
    public Result showList(int page) {
        Result result = new Result();
        result.setData(firstTypeDao.findAll(
                PageRequest.of(page-1,10)).getContent());
        result.setCount(firstTypeDao.count());
        return result;
    }

    public void addFirstType(FirstType firstType) throws Exception {
        firstTypeDao.save(firstType);
    }

    public void delFirstType(FirstType firstType) throws Exception {
        firstTypeDao.deleteById(firstType.getFid());
    }

    @Override
    public void editFirstType(FirstType firstType) throws Exception {
        firstTypeDao.save(firstType);
    }
}
