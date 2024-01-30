package com.project.management.projectmanagement.controller;

import com.project.management.projectmanagement.dto.ProjectDetailsDto;
import com.project.management.projectmanagement.service.ProjectManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<List<ProjectDetailsDto>> fetchAllProjects() {
        log.info("Request received to fetch all available projects.");
        return ResponseEntity.ok(projectManagementService.getAllProjects());
    }
}
