package com.igeek.university.entity;

import lombok.Data;

@Data
public class SpecialtyDto {

    private String address;//所在地
    private String xname; //首选科目名称:物理类，历史类
    private String ename; //再选课要求
    private String uname; //学校名称
    private String remark;//备注
    private String feature;//学校特色
    private String group_name; //专业组名称
    private String scode; //专业代号
    private String sname; //专业名称
    private int score;     //分数
    private int ranking;   //排名
    private String length; //学制
    private String money;  //学费
    private int min_score;
    private String get_num;
    private String group_code;
}
