package com.igeek.university.dao;

import com.igeek.university.entity.FallDetection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FallDetectionRepository extends JpaRepository<FallDetection, Long> {
    List<FallDetection> findByUserID(Long userID);
}
