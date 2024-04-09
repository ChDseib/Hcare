package com.igeek.university.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//在选课要求与专业组的关联表

@Data
@Entity
public class CourseGroupDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer eid; //再选课要求id
    private String ename;
    private String group_code;//专业组代号

}
