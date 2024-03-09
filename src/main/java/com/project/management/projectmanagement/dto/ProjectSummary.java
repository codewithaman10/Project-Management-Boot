package com.project.management.projectmanagement.dto;

import com.project.management.projectmanagement.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectSummary {

    private ProjectStatus projectStatus;
    private Integer count;
}
