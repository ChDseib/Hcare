package com.igeek.university.service;

import com.igeek.university.entity.QueryItem;
import com.igeek.university.entity.Result;
import com.igeek.university.entity.Specialty;
import com.igeek.university.entity.WriteItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IFilterService {
    Result query(QueryItem item);
    String query2(String firstType,int score);
    Result write(WriteItem item);

}
