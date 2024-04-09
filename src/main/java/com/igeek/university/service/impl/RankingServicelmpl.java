package com.igeek.university.service.impl;

import com.igeek.university.dao.IRankingDao;
import com.igeek.university.entity.Ranking;
import com.igeek.university.entity.Result;
import com.igeek.university.entity.SpecialtyGroup;
import com.igeek.university.service.IRankingService;
import com.igeek.university.utils.ExcelUtil;
import com.igeek.university.utils.addRankingExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Service
public class RankingServicelmpl implements IRankingService {
    @Autowired
    IRankingDao rankingDao;
    @Override
    public Result showList(int page) {
        Result result = new Result();
        result.setData(rankingDao.findAll(
                PageRequest.of(page-1,10)).getContent());
        result.setCount(rankingDao.count());
        return result;
    }
    @Override
    public List<Ranking> excel(MultipartFile file) throws Exception {
        List<Ranking> list = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            System.out.println("============fileName=============="+file.getOriginalFilename());
            //读取excel表格内容---重点
            list = addRankingExcel.getAllByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.forEach(System.out::println);
        rankingDao.saveAll(list);
        return list;
    }
}
