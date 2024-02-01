package com.project.StudentManagement.mapper;

import com.project.StudentManagement.dto.CourseDTO;
import com.project.StudentManagement.entity.Course;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface CourseMapper {
    CourseDTO entityToDTO(Course course);
    Course dtoToEntity(CourseDTO courseDTO);

}
