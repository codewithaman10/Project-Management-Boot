package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.dto.*;
import com.project.management.projectmanagement.enums.ProjectStatus;
import com.project.management.projectmanagement.service.ProjectManagementService;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectsController {

    private final ProjectManagementService projectManagementService;

    @Autowired
    public ProjectsController(ProjectManagementService projectManagementService) {
        this.projectManagementService = projectManagementService;
    }

    /*@PostMapping("/add-project")
    public ResponseEntity<String> addNewProject(@RequestBody ProjectDetailsDto project) {
        return ResponseEntity.ok("");
    } */

    @GetMapping("/get-project/{id}")
    public ResponseEntity<ProjectDetailsDto> fetchProjectWithId(@PathVariable String id) {
        log.info("Request received to fetch project details for id: {}", id);
        Optional<ProjectDetailsDto> project = projectManagementService.fetchProject(Integer.parseInt(id));

        return project.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));

    }

    @GetMapping("/get-all-projects")
    public ResponseEntity<List<ProjectLightDto>> fetchAllProjects() {
        log.info("Request received to fetch all available projects.");
        return ResponseEntity.ok(projectManagementService.getAllProjects());
    }

    @PostMapping(value = "add-new-task", consumes = "application/json")
    public ResponseEntity<TaskDto> addNewTaskForProject(@RequestBody TaskDto task) {
        log.info("Request received to add new task for project {}.", task.getProjectId());
        // Save the new Task in task table for project
        TaskDto addedTask = projectManagementService.addNewTask(task);
        if (Objects.nonNull(addedTask)) {
            log.info("Task is successfully saved in database: {} - {}.", addedTask.getProjectId(), addedTask);
            return ResponseEntity.ok(addedTask);
        }

        log.info("Error while saving the task.");
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete-task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id) {
        projectManagementService.deleteTaskForId(Integer.parseInt(id));
        return ResponseEntity.ok("Task successfully deleted.");
    }

    @PutMapping(value = "update-existing-task", consumes = "application/json")
    public ResponseEntity<TaskDto> updateExistingTask(@RequestBody TaskDto task) {
        log.info("Request received to update task for projectid-taskId {}-{}.", task.getProjectId(), task.getId());
        // Update the Task in task table for project
        TaskDto updatedTask = projectManagementService.updateTask(task);
        if (Objects.nonNull(updatedTask)) {
            log.info("Task is successfully updated in database: {} - {}.", updatedTask.getProjectId(), updatedTask);
            return ResponseEntity.ok(updatedTask);
        }

        log.info("Error while saving the task.");
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "add-new-project", consumes = "application/json")
    public ResponseEntity<ProjectLightDto> addNewProject(@RequestBody ProjectDto project) {
        log.info("New Project: {}", project);
        log.info("Request received to add project - {}", project.getTitle());
        ProjectLightDto newProject = projectManagementService.addNewProject(project);

        if(Objects.nonNull(newProject)) {
            return ResponseEntity.ok(newProject);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "delete-project/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable String id) {
        projectManagementService.deleteProjectForId(Integer.parseInt(id));
        return ResponseEntity.ok("Project and related tasks successfully deleted.");
    }

    @GetMapping(value = "fetch-project-summary")
    public ResponseEntity<List<ProjectSummary>> getProjectsSummary() {
        /*try {
            Thread.sleep(10000);
        } catch (Exception e) {
            //
        }*/
        return ResponseEntity.ok(projectManagementService.getProjectsSummary());
    }
}
