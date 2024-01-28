package com.project.management.projectmanagement.dao;

import com.project.management.projectmanagement.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksDao extends JpaRepository<Tasks, Integer> {

}
