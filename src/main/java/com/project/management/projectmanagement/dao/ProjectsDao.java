package com.project.management.projectmanagement.dao;

import com.project.management.projectmanagement.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsDao extends JpaRepository<Project, Integer> {
}
