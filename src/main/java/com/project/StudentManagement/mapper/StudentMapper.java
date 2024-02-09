package com.project.StudentManagement.mapper;

import com.project.StudentManagement.dto.StudentDTO;
import com.project.StudentManagement.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = StudentMapper.class)
public interface StudentMapper {
    @Mapping(target = "fullName", expression = "java(student.getFirstName() + \" \" + student.getLastName())")
    @Mapping(target = "totalCourses", expression = "java(student.getCourses() != null ? student.getCourses().size() : 0)")
    StudentDTO entityToDTO(Student student);

//    default StudentDTO entityToDTO(Student student) {
//        if (student == null) {
//            return null;
//        }
//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setFullName(student.getFirstName() + " " + student.getLastName());
//        return studentDTO;
//    }

    Student dtoToEntity(StudentDTO studentDTO);
}
