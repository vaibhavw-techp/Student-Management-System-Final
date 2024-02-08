package com.project.StudentManagement.mapper;

import com.project.StudentManagement.dto.AddressDTO;
import com.project.StudentManagement.dto.CourseDTO;
import com.project.StudentManagement.dto.UpdateCourseDTO;
import com.project.StudentManagement.entity.Address;
import com.project.StudentManagement.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface AddressMapper {
    AddressDTO entityToDTO(Address address);
    Address dtoToEntity(AddressDTO addressDTO);
}
