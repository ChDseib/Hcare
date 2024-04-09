package com.igeek.university.dao;

import com.igeek.university.entity.CourseGroupDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseGroupDtoDao extends JpaRepository<CourseGroupDto,Integer>{

}