package com.igeek.university;

import com.igeek.university.dao.ICategoryDao;
import com.igeek.university.dao.IGroupRankDao;
import com.igeek.university.dao.ISpecialtyGroupDao;
import com.igeek.university.dao.IUniversityDao;
import com.igeek.university.entity.WordSpe;
import com.igeek.university.utils.AddCategoryExcel;
import com.igeek.university.utils.GroupRankExcel;
import com.igeek.university.utils.OperateExcel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class UniversityApplicationTests {

    @Autowired
    IUniversityDao dao;

    @Autowired
    ICategoryDao categoryDao;

    @Autowired
    ISpecialtyGroupDao specialtyGroupDao;

    @Autowired
    IGroupRankDao groupRankDao;

    //插入高校
    @Test
    void contextLoads() {
        //categoryDao.saveAll(AddCategoryExcel.getData());
        //dao.saveAll(OperateExcel.getData());
    }

    @Test
    void test1() {
        //groupRankDao.saveAll(GroupRankExcel.getData());

    }
}
