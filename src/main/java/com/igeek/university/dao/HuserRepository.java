package com.igeek.university.dao;// HuserRepository.java
import com.igeek.university.entity.Huser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuserRepository extends JpaRepository<Huser, Integer> {
    Huser findByUsernameAndPassword(String username, String password);
    // 可以根据需要添加自定义的方法
}
