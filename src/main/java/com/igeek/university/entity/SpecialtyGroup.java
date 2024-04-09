package com.igeek.university.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//高校专业组
@Data
@Entity
public class SpecialtyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;
    private String code;//院校代号
    private String group_code;//专业组代号
    private String group_name;//专业组名称
    private String year;//年份 2021
}
