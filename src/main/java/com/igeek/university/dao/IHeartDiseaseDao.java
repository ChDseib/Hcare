package com.igeek.university.dao;

import com.igeek.university.entity.HeartDisease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHeartDiseaseDao extends JpaRepository<HeartDisease, Integer> {

}