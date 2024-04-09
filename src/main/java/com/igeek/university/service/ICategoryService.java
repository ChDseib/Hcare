package com.igeek.university.service;

import com.igeek.university.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getCategoryPid();
    List<Category> getCategoryId(String pid);
}
