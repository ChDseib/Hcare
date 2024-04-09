package com.igeek.university.controller;

import com.igeek.university.entity.*;
import com.igeek.university.service.IFilterService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@RestController
public class FilterController {
    @Autowired
    IFilterService filterService;

    @PostMapping("/query")
    public Result query(QueryItem queryItem){
        Result result = new Result();
        if(queryItem.getAddress() != null ){
            return filterService.query(queryItem);
        }
        return result;
    }

    @GetMapping("/query2")
    public String query2(String firstType,int score){
        //System.out.println("-----------------"+firstType+"---"+score);

        return filterService.query2(firstType,score);
    }


    @PostMapping("/write")
    public Result write(WriteItem writeItem){
        Result result = new Result();
        //System.out.println(writeItem);
        if(writeItem.getFirstType() !=null){
            return filterService.write(writeItem);
        }
        return result;
    }

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response){
        try {
            //用于输出响应流
            //本地环境
            //File file = new File("./Word/模拟报考.doc");
            //Linux环境
            File file = new File("/root/Word/模拟报考.doc");
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            OutputStream out = response.getOutputStream();
            int size = in.available();

            String fileName = "模拟报考.doc";

            //设置响应头信息
            // rep.setContentType("Image/" + fileName); // 设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache"); // 设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            response.setContentType("application/octet-stream");
            //输出并关闭
            while(size > 0){
                byte[] bytes = new byte[size];
                in.read(bytes);
                out.write(bytes);
                size = in.available();
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
