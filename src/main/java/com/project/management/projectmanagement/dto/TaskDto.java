package com.project.management.projectmanagement.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class TaskDto {

    private int projectId;
    private int id;
    private String title;
    private String createdBy;
    private LocalDateTime createdAt;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedAt;
    private boolean done;

    public TaskDto(int projectId, int id, String title, String createdBy, LocalDateTime createdAt, String lastUpdatedBy, LocalDateTime lastUpdatedAt, boolean done) {
        this.projectId = projectId;
        this.id = id;
        this.title = title;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedAt = lastUpdatedAt;
        this.done = done;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
