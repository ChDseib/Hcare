package com.igeek.university.dao;

import com.igeek.university.entity.FirstType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFirstTypeDao extends JpaRepository<FirstType,Integer> {
}