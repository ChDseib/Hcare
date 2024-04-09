package com.igeek.university.dao;

import com.igeek.university.entity.HealthData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthDataDao extends JpaRepository<HealthData, Long> {
    List<HealthData> findByUserId(Long userId);
}
