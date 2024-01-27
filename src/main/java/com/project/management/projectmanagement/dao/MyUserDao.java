package com.project.management.projectmanagement.dao;

import com.project.management.projectmanagement.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserDao extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUserName(String username);
}
