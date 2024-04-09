package com.igeek.university.controller;

import com.igeek.university.entity.FirstType;
import com.igeek.university.entity.Result;
import com.igeek.university.service.IFirstTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firsttype")
public class FirstTypeController {
    @Autowired
    IFirstTypeService firstTypeService;

    @GetMapping("/list")
    public Result list(int page){
        return firstTypeService.showList(page);
    }

    @PostMapping("/add")
    public String add(FirstType firstType){
        System.out.println("---firsttype---add-----"+firstType);
        try {
            firstTypeService.addFirstType(firstType);
        } catch (Exception e) {
            e.printStackTrace();
            return "新增首选科目失败！";
        }
        return "新增首选科目成功！";

    }

    @PostMapping("/del")
    public String del(FirstType firstType){
        System.out.println("---firsttype---del-----"+firstType);
        try {
            firstTypeService.delFirstType(firstType);
        } catch (Exception e) {
            e.printStackTrace();
            return "删除首选科目失败！";
        }
        return "删除首选科目成功！";

    }

    @PostMapping("/edit")
    public String edit(FirstType firstType){
        System.out.println("---firsttype---edit-----"+firstType);
        try {
            firstTypeService.editFirstType(firstType);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改首选科目失败！";
        }
        return "修改首选科目成功！";

    }
}
