package com.igeek.university.dao;

import com.igeek.university.entity.HeartRateAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartRateAlertDAO extends JpaRepository<HeartRateAlert, Integer> {

    List<HeartRateAlert> findAllByUserId(int userId);
}
