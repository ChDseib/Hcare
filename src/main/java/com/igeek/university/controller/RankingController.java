package com.igeek.university.controller;

import com.igeek.university.entity.Ranking;
import com.igeek.university.entity.Result;
import com.igeek.university.service.IRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    @Autowired
    IRankingService rankingService;

    @GetMapping("/list")
    public Result list(int page){
        return rankingService.showList(page);
    }





    @PostMapping("/fileUpload")
    @ResponseBody
    public List<Ranking> fileUpload(@RequestPart(value = "file") MultipartFile file) throws Exception {
        return rankingService.excel(file);
    }

}
