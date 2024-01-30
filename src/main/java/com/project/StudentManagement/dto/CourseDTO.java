package com.project.StudentManagement.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Integer id;

    @NotEmpty
    private String title;

    @NotEmpty
    private double courseCode;

    @NotEmpty
    private int credits;
    private double fee;

}
