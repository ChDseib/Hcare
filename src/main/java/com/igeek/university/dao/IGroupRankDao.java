package com.igeek.university.dao;

import com.igeek.university.entity.FirstType;
import com.igeek.university.entity.GroupRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupRankDao extends JpaRepository<GroupRank,Integer> {
}
