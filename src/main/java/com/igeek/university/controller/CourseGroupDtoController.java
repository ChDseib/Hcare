package com.igeek.university.controller;

import com.igeek.university.entity.CourseGroupDto;
import com.igeek.university.entity.Result;
import com.igeek.university.service.ICourseGroupDtoService;
import com.igeek.university.service.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coursegroupdto")
public class CourseGroupDtoController {
    @Autowired
    ICourseGroupDtoService courseGroupDtoService;

    @GetMapping("/list")
    public Result list(int page){
        return courseGroupDtoService.showList(page);
    }


    @PostMapping("/add")
    public String add(CourseGroupDto coursegroupdto) {
        System.out.println("----add----" + coursegroupdto);
        try {
            courseGroupDtoService.addCourseGroupDto(coursegroupdto);
        } catch (Exception e) {
            e.printStackTrace();
            return "新增再选课要求失败";
        }
        return "新增再选课要求成功";
    }

    @PostMapping("/del")
    public String del(int id){
        System.out.println("---del----"+id);
        try {
            courseGroupDtoService.delCourseGroupDto(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "删除再选课要求失败!";
        }
        return "删除再选课要求成功!";
    }

    @PostMapping("/edit")
    public String edit(CourseGroupDto coursegroupdto){
        System.out.println("---edit----"+coursegroupdto);
        try {
            courseGroupDtoService.editCourseGroupDto(coursegroupdto);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改再选课要求失败!";
        }
        return "修改再选课要求成功成功!";
    }

}

