package com.igeek.university.entity;

import lombok.Data;

import java.util.List;

/*返回给页面的数据*/
@Data
public class Result {
    private int code=0;
    private String msg="";
    private long count;
    private List<?> data;
}
