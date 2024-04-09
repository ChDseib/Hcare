package com.igeek.university.dao;

import com.igeek.university.entity.Category;
import com.igeek.university.entity.GroupRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ICategoryDao extends JpaRepository<Category,Integer> {
    @Query(value="select distinct(category_pid) pname from category",nativeQuery = true)
    List<Map> findPid();

    @Query(value="select distinct(category_id) cid from category where category_pid=?1",nativeQuery = true)
    List<Map> findByPid(String pid);

    List<Category> findByCategoryId(String gid);



}
