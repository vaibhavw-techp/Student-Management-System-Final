package com.project.StudentManagement.mapper;

import com.project.StudentManagement.dto.StudentDTO;
import com.project.StudentManagement.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = StudentMapper.class)

public interface StudentMapper {
    StudentDTO entityToDTO(Student student);
    Student dtoToEntity(StudentDTO studentDTO);
}
