package com.igeek.university.service.impl;

import com.igeek.university.dao.ICourseGroupDtoDao;
import com.igeek.university.dao.ISpecialtyDao;
import com.igeek.university.entity.CourseGroupDto;
import com.igeek.university.entity.Result;
import com.igeek.university.service.ICourseGroupDtoService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CourseGroupDtoServiceImpl implements ICourseGroupDtoService {
    @Autowired
    ICourseGroupDtoDao courseGroupDtoDao;

    @Override
    public Result showList(int page) {
        Result result = new Result();
        result.setData(courseGroupDtoDao.findAll(
                PageRequest.of(page -1,10)).getContent());
        result.setCount(courseGroupDtoDao.count());
        return result;
    }

    @Override
    public void addCourseGroupDto(CourseGroupDto courseGroupDto) {
        courseGroupDtoDao.save(courseGroupDto);
    }

    @Override
    public void delCourseGroupDto(int id) throws Exception {
        courseGroupDtoDao.deleteById(id);
    }

    @Override
    public void editCourseGroupDto(CourseGroupDto courseGroupDto) throws Exception {
        courseGroupDtoDao.save(courseGroupDto);
    }
}
