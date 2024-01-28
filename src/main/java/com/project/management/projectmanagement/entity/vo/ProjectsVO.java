package com.project.management.projectmanagement.entity.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProjectsVO {

    private Long projectId;
    private String title;
    private String description;
    private String createdBy;
    private String lastUpdatedBy;
    private LocalDate dueDate;
    private LocalDateTime lastUpdatedAt;

    public ProjectsVO(Long projectId, String title, String description, String createdBy, String lastUpdatedBy, LocalDate dueDate, LocalDateTime lastUpdatedAt) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
        this.dueDate = dueDate;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public String toString() {
        return "ProjectsVO{" +
                "projectId=" + projectId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", dueDate=" + dueDate +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }
}
