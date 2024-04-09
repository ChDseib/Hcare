package com.igeek.university.dao;

import com.igeek.university.entity.SleepData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SleepDataRepository extends JpaRepository<SleepData, Long> {

    List<SleepData> findByUserID(Long userID);
}
