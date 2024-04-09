package com.igeek.university.controller;

import com.igeek.university.entity.Result;
import com.igeek.university.entity.SpecialtyGroup;
import com.igeek.university.service.ISpecialtyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/specialtygroup")
public class SpecialtyGroupController {
    @Autowired
    ISpecialtyGroupService specialtygroupService;

    @GetMapping("/list")
    public Result list(int page){
        return specialtygroupService.showList(page);
    }

    @PostMapping("/add")
    public String add(SpecialtyGroup specialtyGroup){
        String year=Calendar.getInstance().get(Calendar.YEAR)+"";
        specialtyGroup.setYear(year);
        System.out.println("---specialtygroup---add-----"+specialtyGroup);
        try {
            specialtygroupService.addSpecialtyGroup(specialtyGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return "新增专业组失败！";
        }
        return "新增专业组成功！";
    }

    @PostMapping("/del")
    public String del(int gid){
        System.out.println("---specialtygroup---del-----"+gid);
        try {
            specialtygroupService.delSpecialtyGroup(gid);
        } catch (Exception e) {
            e.printStackTrace();
            return "删除专业组失败！";
        }
        return "删除专业组成功！";
    }

    @PostMapping("/edit")
    public String edit(SpecialtyGroup specialtyGroup){
        System.out.println("---specialtygroup---edit-----"+specialtyGroup);
        try {
            specialtygroupService.editSpecialtyGroup(specialtyGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改专业组失败！";
        }
        return "修改专业组成功！";
    }


    @PostMapping("/fileUpload")
    @ResponseBody
    public List<SpecialtyGroup> fileUpload(@RequestPart(value = "file") MultipartFile file) throws Exception {
        return specialtygroupService.excel(file);
    }

}
