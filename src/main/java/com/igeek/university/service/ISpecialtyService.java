package com.igeek.university.service;

import com.igeek.university.entity.Result;
import com.igeek.university.entity.Specialty;
import com.igeek.university.entity.SpecialtyGroup;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISpecialtyService {
    Result showList(int page);
    Result showList(int page,Specialty sp);
    void addSpecialty(Specialty specialty) throws Exception;
    void delSpecialty(Specialty specialty) throws Exception;
    void editSpecialty(Specialty specialty) throws Exception;
    List<Specialty> excel(MultipartFile file) throws Exception;
}
