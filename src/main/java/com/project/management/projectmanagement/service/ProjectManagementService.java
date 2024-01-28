package com.project.management.projectmanagement.service;

import com.project.management.projectmanagement.dao.ProjectsDao;
import com.project.management.projectmanagement.dao.TasksDao;
import com.project.management.projectmanagement.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectManagementService {

    private final ProjectsDao projectsDao;
    private final TasksDao tasksDao;

    @Autowired
    public ProjectManagementService(ProjectsDao projectsDao, TasksDao tasksDao) {
        this.projectsDao = projectsDao;
        this.tasksDao = tasksDao;
    }

    public Project addReceivedProjectToDB(Project project) {
        return projectsDao.save(project);
    }

    public Optional<Project> fetchProject(int id) {
        return projectsDao.findById(id);
    }
}
