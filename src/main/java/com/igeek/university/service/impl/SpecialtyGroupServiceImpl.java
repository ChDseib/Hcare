package com.igeek.university.service.impl;

import com.igeek.university.dao.ISpecialtyGroupDao;
import com.igeek.university.entity.Result;

import com.igeek.university.entity.SpecialtyGroup;
import com.igeek.university.service.ISpecialtyGroupService;
import com.igeek.university.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialtyGroupServiceImpl implements ISpecialtyGroupService {
    @Autowired
    ISpecialtyGroupDao specialtyGroupDao;

    @Override
    public Result showList(int page) {
        Result result = new Result();
        result.setData(specialtyGroupDao.findAll(
                PageRequest.of(page-1,10)).getContent());
        result.setCount(specialtyGroupDao.count());
        return result;
    }

    @Override
    public void addSpecialtyGroup(SpecialtyGroup specialtyGroup) throws Exception {
        specialtyGroupDao.save(specialtyGroup);
    }

    @Override
    public void delSpecialtyGroup(int gid) throws Exception {
        specialtyGroupDao.deleteById(gid);

    }

    @Override
    public void editSpecialtyGroup(SpecialtyGroup specialtyGroup) throws Exception {
        specialtyGroupDao.save(specialtyGroup);
    }

    @Override
    public List<SpecialtyGroup> excel(MultipartFile file) throws Exception {
        List<SpecialtyGroup> list = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            System.out.println("============fileName=============="+file.getOriginalFilename());
            //读取excel表格内容---重点
            list = ExcelUtil.getAllByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.forEach(System.out::println);
        specialtyGroupDao.saveAll(list);
        return list;
    }

}
