package com.igeek.university.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ranking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer rid;
    private String score;//分数段
    private String same_num;//同分人数
    private String ranking;//排名
    private String fname;//首选科目
    private Integer year;//年份
}