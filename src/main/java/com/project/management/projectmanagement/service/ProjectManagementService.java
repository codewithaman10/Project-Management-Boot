package com.project.management.projectmanagement.service;

import com.project.management.projectmanagement.dao.ProjectsDao;
import com.project.management.projectmanagement.dto.ProjectDetailsDto;
import com.project.management.projectmanagement.dto.ProjectDto;
import com.project.management.projectmanagement.entity.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProjectManagementService {

    private final ProjectsDao projectsDao;

    public ProjectManagementService(ProjectsDao projectsDao) {
        this.projectsDao = projectsDao;
    }

    public List<ProjectDetailsDto> getAllProjects() {
        return convertToProjectDetailsDto(projectsDao.findAll());
    }

    public Optional<ProjectDetailsDto> fetchProject(int id) {
        Optional<Project> project = projectsDao.findById(id);

        return project.map(this::convertProjectToDto);

    }

    private List<ProjectDetailsDto> convertToProjectDetailsDto(List<Project> projects) {
        List<ProjectDetailsDto> pds = new ArrayList<>();
        projects.forEach(project -> {
            pds.add(convertProjectToDto(project));
        });

        return pds;
    }

    private ProjectDetailsDto convertProjectToDto(Project project) {
        return new ProjectDetailsDto(
                new ProjectDto(project.getProjectId(), project.getTitle(), project.getDescription(),
                        project.getDueDate(), project.getCreatedBy(), project.getCreatedAt(),
                        project.getLastUpdatedBy(), project.getLastUpdatedAt(), project.isCompleted())
                , null);
    }
}
