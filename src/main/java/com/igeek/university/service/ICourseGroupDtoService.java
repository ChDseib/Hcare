package com.igeek.university.service;

import com.igeek.university.entity.CourseGroupDto;
import com.igeek.university.entity.Result;
import org.apache.poi.ss.formula.functions.T;

public interface ICourseGroupDtoService {
    Result showList(int page);
    void addCourseGroupDto(CourseGroupDto coursegroupdto) throws Exception;
    void delCourseGroupDto(int id) throws Exception;
    void editCourseGroupDto(CourseGroupDto coursegroupdto) throws Exception;
}
