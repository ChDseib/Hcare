package com.igeek.university.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//高校表
@Data
@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    private String name; //学校名称
    private String address;//所在地
    private String level;//办学层次
    private String remark;//备注
    private String feature;//学校特色
    private String code;//院校代号
}
