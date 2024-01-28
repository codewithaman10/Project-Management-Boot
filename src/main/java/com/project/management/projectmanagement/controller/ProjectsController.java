package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.entity.Project;
import com.project.management.projectmanagement.service.ProjectManagementService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Slf4j
public class ProjectsController {
    @Autowired
    private ProjectManagementService projectManagementService;

    @PostMapping("/add-project")
    public ResponseEntity<Project> addNewProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectManagementService.addReceivedProjectToDB(project));
    }

    @GetMapping("/get-project/{id}")
    public ResponseEntity<Project> fetchProjectWithId(@PathVariable String id) {
        log.info("Request received to fetch project details for id: {}", id);
        Optional<Project> project = projectManagementService.fetchProject(Integer.parseInt(id));

        return project.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));

    }
}
