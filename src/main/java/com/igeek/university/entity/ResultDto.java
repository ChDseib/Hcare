package com.igeek.university.entity;

import lombok.Data;

@Data
public class ResultDto {
    private String address;//所在地
    private String xname; //首选科目名称:物理类，历史类
    private String ename; //再选课要求
    private String remark;//备注
    private String feature;//学校特色
    private String group_name; //专业组名称
    private String group_code; //专业组编号
    private int score;     //分数
    private int ranking;   //排名
    private int get_num;   //录取人数
}
