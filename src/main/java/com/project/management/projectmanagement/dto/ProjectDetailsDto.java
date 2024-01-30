package com.project.management.projectmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProjectDetailsDto {

    @JsonProperty("project")
    private ProjectDto project;

    @JsonProperty("tasks")
    private List<TaskDto> tasks;

    public ProjectDetailsDto(ProjectDto project, List<TaskDto> tasks) {
        this.project = project;
        this.tasks = tasks;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
