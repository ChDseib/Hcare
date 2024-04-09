package com.igeek.university.controller;

import com.igeek.university.entity.Result;
import com.igeek.university.entity.Specialty;
import com.igeek.university.entity.SpecialtyGroup;
import com.igeek.university.service.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/specialty")
public class SpecialtyController {
    @Autowired
    ISpecialtyService specialtyService;

    @GetMapping("/list")
    public Result list(int page,Specialty sp){
        System.out.println("---------"+sp);
        if(sp.getName() != null || sp.getSname() != null){
            return specialtyService.showList(page,sp);
        }
        return specialtyService.showList(page);
    }

    @PostMapping("/add")
    public String add(Specialty specialty){
        specialty.setYear(Calendar.getInstance().get(Calendar.YEAR));
        try {
            specialtyService.addSpecialty(specialty);
        } catch (Exception e) {
            e.printStackTrace();
            return "新增专业失败!";
        }
        return "新增专业成功!";
    }

    @PostMapping("/del")
    public String del(Specialty specialty){
        System.out.println("---del----"+specialty);
        try {
            specialtyService.delSpecialty(specialty);
        } catch (Exception e) {
            e.printStackTrace();
            return "删除专业失败!";
        }
        return "删除专业成功!";
    }

    @PostMapping("/edit")
    public String edit(Specialty specialty){
        System.out.println("---edit----"+specialty);
        try {
            specialtyService.editSpecialty(specialty);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改专业失败!";
        }
        return "修改专业成功!";
    }
    @PostMapping("/fileUpload")
    @ResponseBody
    public List<Specialty> fileUpload(@RequestPart(value = "file") MultipartFile file) throws Exception {
        return specialtyService.excel(file);
    }
}
