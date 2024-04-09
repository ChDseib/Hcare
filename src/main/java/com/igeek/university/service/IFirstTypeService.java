package com.igeek.university.service;
import com.igeek.university.entity.FirstType;
import com.igeek.university.entity.Result;
import com.igeek.university.entity.SpecialtyGroup;

public interface IFirstTypeService {
    Result showList(int page);
    void addFirstType(FirstType firstType) throws Exception;
    void delFirstType(FirstType firstType) throws Exception;
    void editFirstType(FirstType firstType) throws Exception;
}
