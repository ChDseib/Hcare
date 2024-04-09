package com.igeek.university.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//首选科目
@Data
@Entity
public class FirstType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fid;
    private String name; //首选科目名称:物理类，历史类
    private String group_code;//院校代号
}
