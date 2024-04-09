package com.igeek.university.controller;

import com.igeek.university.dao.ICategoryDao;
import com.igeek.university.entity.Category;
import com.igeek.university.entity.Ranking;
import com.igeek.university.entity.Result;
import com.igeek.university.service.ICategoryService;
import com.igeek.university.service.IRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping("/selectAllPid")
    public List<Category> selectAllPid(){
        return categoryService.getCategoryPid();
    }

    @GetMapping("/selectAllByPid")
    public List<Category> selectAllByPid(String pid){
        List<Category> list = categoryService.getCategoryId(pid);
        return list;
    }

}
