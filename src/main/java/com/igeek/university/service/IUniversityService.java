package com.igeek.university.service;

import com.igeek.university.entity.Result;
import com.igeek.university.entity.University;

public interface IUniversityService {
    Result showList(int page);
    Result showList(int page,University u);
    void addUniversity(University university) throws Exception;
    void delUniversity(University university) throws Exception;
    void editUniversity(University university) throws Exception;
}
