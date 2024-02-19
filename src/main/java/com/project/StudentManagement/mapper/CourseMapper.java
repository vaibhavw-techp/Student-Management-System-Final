package com.project.StudentManagement.mapper;

import com.project.StudentManagement.dto.CourseDTO;
import com.project.StudentManagement.dto.UpdateCourseDTO;
import com.project.StudentManagement.dto.UpdateStudentDTO;
import com.project.StudentManagement.entity.Course;
import com.project.StudentManagement.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface CourseMapper {
    CourseDTO entityToDTO(Course course);

//    @Mapping(target = "id", ignore = true)
    void updateCourseFromDTO(UpdateCourseDTO updateCourseDTO, @MappingTarget Course course);

    Course dtoToEntity(CourseDTO courseDTO);

}
