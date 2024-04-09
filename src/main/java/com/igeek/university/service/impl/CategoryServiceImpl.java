package com.igeek.university.service.impl;

import com.igeek.university.dao.ICategoryDao;
import com.igeek.university.entity.Category;
import com.igeek.university.entity.SpecialtyDto;
import com.igeek.university.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryDao categoryDao;

    @Override
    public List<Category> getCategoryPid() {
        List<Map> pids = categoryDao.findPid();
        List<Category> list = new ArrayList<>();
        for (Map map : pids) {
            Category category = new Category();
            category.setCategoryPid(map.get("PNAME").toString());
            list.add(category);
        }
        return list;
    }

    @Override
    public List<Category> getCategoryId(String pid) {
        List<Map> pids = categoryDao.findByPid(pid);
        List<Category> list = new ArrayList<>();
        for (Map map : pids) {
            Category category = new Category();
            category.setCategoryId(map.get("CID").toString());
            list.add(category);
        }
        return list;
    }
}
