package com.igeek.university.dao;

import com.igeek.university.entity.University;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUniversityDao extends JpaRepository<University,Integer> {
    List<University> findByNameLikeAndAddressAndRemarkAndFeature(String name, String address, String remark, String feature, Pageable page);
    List<University> findByAddressAndRemarkAndFeature(String address,String remark,String feature, Pageable page);
    int countByNameLikeAndAddressAndRemarkAndFeature(String name, String address, String remark, String feature);
    int countByAddressAndRemarkAndFeature(String address, String remark, String feature);
}
