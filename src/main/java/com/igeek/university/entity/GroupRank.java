package com.igeek.university.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class GroupRank {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer grid;
    private String group_code;//专业组的编号
    private String ranking;//排名
    private Integer year ;//年份
    private String get_num;//录取人数
    private String min_score;//最低分
    private String max_score;//最高分
}


