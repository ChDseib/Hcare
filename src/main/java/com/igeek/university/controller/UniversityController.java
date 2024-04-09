package com.igeek.university.controller;

import com.igeek.university.entity.Result;
import com.igeek.university.entity.University;
import com.igeek.university.service.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    IUniversityService universityService;

    @GetMapping("/list")
    public Result list(int page,University u){
        System.out.println("---------"+u);
        if(u.getRemark() != null){
            return universityService.showList(page,u);
        }
        return universityService.showList(page);
    }

    @PostMapping("/add")
    public String add(University university){
        university.setLevel("本科");
        try {
            universityService.addUniversity(university);
        } catch (Exception e) {
            e.printStackTrace();
            return "新增高校失败！";
        }
        return "新增高校成功！";
    }

    @PostMapping("/del")
    public String del(University university){
        System.out.println("---del----"+university);
        try {
            universityService.delUniversity(university);
        } catch (Exception e) {
            e.printStackTrace();
            return "删除高校失败!";
        }
        return "删除高校成功!";
    }

    @PostMapping("/edit")
    public String edit(University university){
        System.out.println("---edit----"+university);
        try {
            universityService.editUniversity(university);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改高校失败!";
        }
        return "修改高校成功!";
    }
}
