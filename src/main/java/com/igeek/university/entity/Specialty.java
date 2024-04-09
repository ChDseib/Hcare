package com.igeek.university.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sid;
    private String group_code;//专业组代码
    private String scode;//专业代号
    private String name;//专业组名称
    private String sname; //专业名称
    private String plan_num;//计划数
    private String score;//录取分数
    private String ranking;//排名
    private String length;//学制
    private String money;//学费
    private Integer year = 2021;//年份
}
