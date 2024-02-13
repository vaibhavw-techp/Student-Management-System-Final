package com.project.StudentManagement.mapper;

import com.project.StudentManagement.dto.AddressDTO;
import com.project.StudentManagement.dto.CourseDTO;
import com.project.StudentManagement.dto.UpdateCourseDTO;
import com.project.StudentManagement.dto.UpdateStudentDTO;
import com.project.StudentManagement.entity.Address;
import com.project.StudentManagement.entity.Course;
import com.project.StudentManagement.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface AddressMapper {
    AddressDTO entityToDTO(Address address);

    @Mapping(target = "address.id", source = "id")
//    @Mapping(target = "locality", defaultExpression = "java(address.getLocality())")
//    @Mapping(target = "city", defaultExpression = "java(address.getCity())")
    void updateAddressFromDTO(AddressDTO addressDTO, @MappingTarget Address address);

    Address dtoToEntity(AddressDTO addressDTO);
}
