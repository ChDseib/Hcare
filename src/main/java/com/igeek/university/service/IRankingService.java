package com.igeek.university.service;

import com.igeek.university.entity.Ranking;
import com.igeek.university.entity.Result;
import com.igeek.university.entity.SpecialtyGroup;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRankingService {
    Result showList(int page);
    List<Ranking> excel(MultipartFile file) throws Exception;
}
