package com.project.management.projectmanagement.service;

import com.project.management.projectmanagement.dao.ProjectsDao;
import com.project.management.projectmanagement.dao.TasksDao;
import com.project.management.projectmanagement.dto.ProjectDetailsDto;
import com.project.management.projectmanagement.dto.ProjectDto;
import com.project.management.projectmanagement.dto.TaskDto;
import com.project.management.projectmanagement.entity.Project;
import com.project.management.projectmanagement.entity.Tasks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProjectManagementService {

    private final ProjectsDao projectsDao;
    private final TasksDao tasksDao;

    public ProjectManagementService(ProjectsDao projectsDao, TasksDao tasksDao) {
        this.projectsDao = projectsDao;
        this.tasksDao = tasksDao;
    }

    public List<ProjectDetailsDto> getAllProjects() {
        return convertToProjectDetailsDto(projectsDao.findAll());
    }

    public Optional<ProjectDetailsDto> fetchProject(int id) {
        Optional<Project> project = projectsDao.findById(id);

        return project.map(p -> this.convertProjectToDto(p, true));

    }

    private List<ProjectDetailsDto> convertToProjectDetailsDto(List<Project> projects) {
        List<ProjectDetailsDto> pds = new ArrayList<>();
        projects.forEach(project -> {
            pds.add(convertProjectToDto(project, false));
        });

        return pds;
    }

    public TaskDto addNewTask(TaskDto task) {
        // Convert DTO to entity object
        Tasks t = convertDtoToTask(task);
        return saveTask(t);
    }

    public void deleteTaskForId(int id) {
        tasksDao.deleteById(id);
    }

    private TaskDto saveTask(Tasks t) {
        return convertEntityToTaskDto(tasksDao.saveAndFlush(t));
    }

    public TaskDto updateTask(TaskDto taskDto) {
        // Convert DTO to entity object
        Tasks t = convertDtoToTask(taskDto);
        // This is required here to tell hibernate this is an existing task no need to crate a new row in table
        t.setTaskId(taskDto.getId());
        t.setDone(taskDto.isDone());        // Mark as done if user has done the same on UI
        return saveTask(t);
    }

    private Tasks convertDtoToTask(TaskDto taskDto) {
        Tasks tasks = new Tasks();
        tasks.setProjectId(taskDto.getProjectId());
        tasks.setCreatedAt(taskDto.getCreatedAt());
        tasks.setCreatedBy(taskDto.getCreatedBy());
        tasks.setTitle(taskDto.getTitle());
        tasks.setLastUpdatedAt(taskDto.getLastUpdatedAt());
        tasks.setLastUpdatedBy(taskDto.getLastUpdatedBy());
        return tasks;
    }

    private TaskDto convertEntityToTaskDto(Tasks task) {
        return new TaskDto(task.getProjectId(), task.getTaskId(), task.getTitle(),
                task.getCreatedBy(), task.getCreatedAt(), task.getLastUpdatedBy(), task.getLastUpdatedAt(),
                task.isDone());
    }

    private ProjectDetailsDto convertProjectToDto(Project project, boolean loadTasks) {
        List<TaskDto> tasks = new ArrayList<>();

        // Because I don't want to fetch tasks when i am just querying for all the projects
        // Only required when someone tries to fetch single project details
        if (loadTasks) {
            project.getTasks().forEach(task -> tasks.add(convertEntityToTaskDto(task)));
        }

        return new ProjectDetailsDto(
                new ProjectDto(project.getProjectId(), project.getTitle(), project.getDescription(),
                        project.getDueDate(), project.getCreatedBy(), project.getCreatedAt(),
                        project.getLastUpdatedBy(), project.getLastUpdatedAt(), project.isCompleted())
                , tasks);
    }
}
