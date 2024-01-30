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
public class StudentDTO {
    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String year;

    @NotEmpty
    private String dept;

}
