package com.igeek.university.entity;

import lombok.Data;

@Data
public class WriteItem {
    private String address;
    private String firstType;//首选科目
    private String twoType;//再选科目
    private int score;//分数
    private String categoryId;

}
