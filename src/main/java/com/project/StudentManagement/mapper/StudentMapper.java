package com.project.StudentManagement.mapper;

import com.project.StudentManagement.dto.StudentAddressDTO;
import com.project.StudentManagement.dto.StudentDTO;
import com.project.StudentManagement.dto.UpdateStudentDTO;
import com.project.StudentManagement.entity.Student;
import org.mapstruct.*;

// There are two ways for default

@Mapper(componentModel = "spring", uses = StudentMapper.class,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {
    @Mapping(target = "fullName", expression = "java(student.getFirstName() + \" \" + student.getLastName())")
    @Mapping(target = "totalCourses", expression = "java(student.getCourses() != null ? student.getCourses().size() : 0)")
    StudentDTO entityToDTO(Student student);

    @Mapping(target = "firstName", source = "updateStudentDTO.fullName", qualifiedByName = "extractFirstName", defaultExpression = "java(student.getFirstName())")
    @Mapping(target = "lastName", source = "updateStudentDTO.fullName", qualifiedByName = "extractLastName", defaultExpression = "java(student.getLastName())")
    @Mapping(target = "year", defaultExpression = "java(student.getYear())")
    @Mapping(target = "dept", source = "updateStudentDTO.dept")
    void updateStudentFromDTO(UpdateStudentDTO updateStudentDTO, @MappingTarget Student student);

    @Mapping(target = "firstName", source = "studentAddressDTO.fullName", qualifiedByName = "extractFirstName", defaultExpression = "java(student.getFirstName())")
    @Mapping(target = "lastName", source = "studentAddressDTO.fullName", qualifiedByName = "extractLastName", defaultExpression = "java(student.getLastName())")
    @Mapping(target = "year", defaultExpression = "java(student.getYear())")
    @Mapping(target = "dept", source = "studentAddressDTO.dept")
    void updateStudentFromStudentAddressDTO(StudentAddressDTO studentAddressDTO, @MappingTarget Student student);



    @Named("extractFirstName")
    default String extractFirstName(String fullName) {
        if (fullName == null) {
            return null;
        }
        int spaceIndex = fullName.indexOf(' ');
        String firstName = spaceIndex != -1 ? fullName.substring(0, spaceIndex) : null;

        return firstName;
    }

    @Named("extractLastName")
    default String extractLastName(String fullName) {
        if (fullName == null) {
            return null;
        }
        int spaceIndex = fullName.indexOf(' ');
        String lastName = spaceIndex != -1 ? fullName.substring(spaceIndex + 1) : "";
        return lastName;

    }

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
