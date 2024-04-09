package com.igeek.university.dao;

import com.igeek.university.entity.Ranking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRankingDao extends JpaRepository<Ranking,Integer> {
}
