package com.igeek.university.controller;
import com.igeek.university.entity.GroupRank;
import com.igeek.university.entity.Result;
import com.igeek.university.entity.SpecialtyGroup;
import com.igeek.university.service.IGrouprankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("grouprank")
public class GrouprankController {
    @Autowired
    IGrouprankService grouprankService;

    @GetMapping("/list")
    public Result list(int page) {
        return grouprankService.showList(page);
    }

    @PostMapping("/add")
    public String add(GroupRank groupRank){
        System.out.println("---grouprank---add-----"+groupRank);
        try {
            grouprankService.addGroupRank(groupRank);
        } catch (Exception e) {
            e.printStackTrace();
            return "新增失败！";
        }
        return "新增成功！";
    }

    @PostMapping("/del")
    public String del(int grid){
        System.out.println("---grouprank---del-----"+grid);
        try {
            grouprankService.delGroupRank(grid);
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败！";
        }
        return "删除成功！";
    }

    @PostMapping("/edit")
    public String edit(GroupRank groupRank){
        System.out.println("---grouprank---edit-----"+groupRank);
        try {
            grouprankService.editGroupRank(groupRank);
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败！";
        }
        return "修改成功！";
    }


    @PostMapping("/fileUpload")
    @ResponseBody
    public List<GroupRank> fileUpload(@RequestPart(value = "file") MultipartFile file) throws Exception {
      return grouprankService.excel(file);
    }
}
