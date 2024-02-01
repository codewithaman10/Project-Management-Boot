package com.project.management.projectmanagement.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProjectLightDto {

    private int id;
    private String title;
    private LocalDate dueDate;

    public ProjectLightDto(int id, String title, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
