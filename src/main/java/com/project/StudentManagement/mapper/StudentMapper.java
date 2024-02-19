package com.project.StudentManagement.mapper;

import com.project.StudentManagement.dto.*;
import com.project.StudentManagement.entity.Address;
import com.project.StudentManagement.entity.Student;
import org.mapstruct.*;

// There are two ways for default

@Mapper(componentModel = "spring", uses = AddressMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
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
//    @Mapping(target = "year", defaultExpression = "java(student.getYear())")
//    @Mapping(target = "dept", source = "studentAddressDTO.dept")
    void updateStudentFromStudentAddressDTO(UpdateStudentAddressDTO studentAddressDTO, @MappingTarget Student student);


    @Mapping(target = "firstName", source = "studentAddressDTO.fullName", qualifiedByName = "extractFirstName", defaultExpression = "java(student.getFirstName())")
    @Mapping(target = "lastName", source = "studentAddressDTO.fullName", qualifiedByName = "extractLastName", defaultExpression = "java(student.getLastName())")
    @Mapping(target = "year", defaultExpression = "java(student.getYear())")
    @Mapping(target = "dept", source = "studentAddressDTO.dept")
    @Mapping(target = "student.addresses", source = "studentAddressDTO.addresses")
    void updateStudentData(StudentAddressDTO studentAddressDTO, @MappingTarget Student student);

    @Mapping(target = "fullName", expression = "java(getFullName(student.getFirstName(),student.getLastName()))")
    void convertEntityToStudentAddressDTO(@MappingTarget StudentAddressDTO studentAddressDTO, Student student);


    @AfterMapping
    public default void setStudentToAddresses(StudentAddressDTO studentAddressDTO, @MappingTarget Student student){
        for (Address address: student.getAddresses()){
            address.setStudent(student);
        }
    }

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

    default String getFullName(String firstName, String lastName){
        return firstName + " " + lastName;
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
    UpdateStudentAddressDTO StudentAddressDtoToUpdateStudentDto(StudentAddressDTO studentAddressDTO);
}
