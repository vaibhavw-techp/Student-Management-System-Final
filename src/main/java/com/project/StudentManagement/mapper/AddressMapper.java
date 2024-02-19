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

import java.util.List;

@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface AddressMapper {
    AddressDTO entityToDTO(Address address);

//    @Mapping(target = "address.id", source = "id")
    @Mapping(target = "address.id", source = "addressDTO.id")
    @Mapping(target = "address.locality", source = "addressDTO.locality")
    @Mapping(target = "address.city", source = "addressDTO.city")
    @Mapping(target = "address.pincode", source = "addressDTO.pincode")
    void updateAddressFromDTO(AddressDTO addressDTO, @MappingTarget Address address);

    List<Address> getEntityList(List<AddressDTO> addressesDTOS);
    Address dtoToEntity(AddressDTO addressDTO);
}
