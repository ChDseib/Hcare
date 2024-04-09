package com.igeek.university.service;

import com.igeek.university.entity.Result;
import com.igeek.university.entity.SpecialtyGroup;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ISpecialtyGroupService {
    Result showList(int page);
    void addSpecialtyGroup(SpecialtyGroup specialtyGroup) throws Exception;
    void delSpecialtyGroup(int gid) throws Exception;
    void editSpecialtyGroup(SpecialtyGroup specialtyGroup) throws Exception;
    List<SpecialtyGroup> excel(MultipartFile file) throws Exception;

    }
