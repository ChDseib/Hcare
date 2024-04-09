package com.igeek.university.service.impl;

import com.igeek.university.dao.ISpecialtyDao;
import com.igeek.university.entity.Result;
import com.igeek.university.entity.Specialty;
import com.igeek.university.entity.SpecialtyGroup;
import com.igeek.university.entity.University;
import com.igeek.university.service.ISpecialtyService;
import com.igeek.university.utils.SpecialtyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class specialtyServiceImpl implements ISpecialtyService {
    @Autowired
    ISpecialtyDao specialtyDao;

    @Override
    public Result showList(int page) {
        Result result = new Result();
        result.setData(specialtyDao.findAll(
                PageRequest.of(page -1,10)).getContent());
        result.setCount(specialtyDao.count());
        return result;
    }

    @Override
    public Result showList(int page, Specialty sp) {
        Result result = new Result();
        List<Specialty> list = new ArrayList<>();
        int count=0;
        if(!sp.getName().equals("") && !sp.getSname().equals("")){

            list = specialtyDao.findByNameContainingAndSnameContainingAndYear(sp.getName(),sp.getSname(),sp.getYear(),PageRequest.of(page -1,10));
            count = specialtyDao.countByNameContainingAndSnameContainingAndYear(sp.getName(),sp.getSname(),sp.getYear());
        }else if(sp.getName().equals("") && !sp.getSname().equals("")){

            list = specialtyDao.findBySnameContainingAndYear(sp.getSname(),sp.getYear(),PageRequest.of(page -1,10)) ;
            count = specialtyDao.countBySnameContainingAndYear(sp.getSname(),sp.getYear());
        }else if(!sp.getName().equals("") && sp.getSname().equals("")){

            list = specialtyDao.findByNameContainingAndYear(sp.getName(),sp.getYear(),PageRequest.of(page -1,10)) ;
            count = specialtyDao.countByNameContainingAndYear(sp.getName(),sp.getYear());
        }else{
            list = specialtyDao.findByYear(sp.getYear(),PageRequest.of(page -1,10));
            count = specialtyDao.countByYear(sp.getYear());
        }
        result.setData(list);
        result.setCount(count);
        return result;
    }

    @Override
    public void addSpecialty(Specialty specialty) throws Exception {
        specialtyDao.save(specialty);
    }

    @Override
    public void delSpecialty(Specialty specialty) throws Exception {
        specialtyDao.deleteById(specialty.getSid());
    }

    @Override
    public void editSpecialty(Specialty specialty) throws Exception {
        specialtyDao.save(specialty);
    }

    @Override
    public List<Specialty> excel(MultipartFile file) throws Exception {
        List<Specialty> list = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            System.out.println("============fileName=============="+file.getOriginalFilename());
            //读取excel表格内容---重点
            list = SpecialtyUtil.getAllByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.forEach(System.out::println);
        specialtyDao.saveAll(list);
        return list;
    }

}
