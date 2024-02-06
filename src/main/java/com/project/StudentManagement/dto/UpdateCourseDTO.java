package com.project.StudentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateCourseDTO {
    private String title;
    private double courseCode;
    private int credits;
    private double fee;
}
