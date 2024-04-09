package com.igeek.university.dao;

import com.igeek.university.entity.SpecialtyGroup;
import com.igeek.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpecialtyGroupDao extends JpaRepository<SpecialtyGroup,Integer> {
}
