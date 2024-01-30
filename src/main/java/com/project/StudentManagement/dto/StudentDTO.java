package com.project.StudentManagement.dto;

<<<<<<< HEAD
import com.project.StudentManagement.requestforpost.CourseRequest;
import com.project.StudentManagement.dto.CourseDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
=======
import jakarta.validation.constraints.NotEmpty;
>>>>>>> e0d1085 (Validations added in DTOs)
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Integer id;
    private String fullName;
    private String firstName;
    private String lastName;
    private String year;

    @NotEmpty
    private String dept;
    private int totalCourses;

    private Set<CourseDTO> courses;


}
