package com.igeek.university.entity;

import lombok.Data;

@Data
public class QueryItem {

    private String address;//选择省份
    private String firstType;//首选科目
    private String twoType;//再选科目
    private int score_min;//分数
    private int score_max;//分数
    private int ranking;//省内排名

}
