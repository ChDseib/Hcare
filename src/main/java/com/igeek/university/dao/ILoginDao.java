package com.igeek.university.dao;

import com.igeek.university.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ILoginDao extends JpaRepository<User,Integer> {
    @Query(value = "select * from user where uname=? and pwd=?",nativeQuery = true)
    List<User> query(String uname,String pwd);


}
