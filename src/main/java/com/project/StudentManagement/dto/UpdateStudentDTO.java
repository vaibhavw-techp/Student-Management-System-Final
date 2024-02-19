package com.project.StudentManagement.dto;



import com.project.StudentManagement.requestforpost.CourseRequest;
import com.project.StudentManagement.dto.CourseDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentDTO
{
    private String fullName;
    private String year;
    private String dept;

    private Set<CourseDTO> courses;

}
