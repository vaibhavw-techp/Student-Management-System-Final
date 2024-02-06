package com.project.StudentManagement.mapper;

import com.project.StudentManagement.dto.CourseDTO;
import com.project.StudentManagement.dto.UpdateCourseDTO;
import com.project.StudentManagement.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", uses = CourseMapper.class,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {
    CourseDTO entityToDTO(Course course);

    void updateCourseFromDTO(UpdateCourseDTO updateCourseDTO, @MappingTarget Course course);
    Course dtoToEntity(CourseDTO courseDTO);



}
