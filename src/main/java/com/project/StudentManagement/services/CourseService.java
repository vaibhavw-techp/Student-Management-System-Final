package com.project.StudentManagement.services;

import com.project.StudentManagement.dto.CourseDTO;
import com.project.StudentManagement.dto.StudentDTO;
import com.project.StudentManagement.dto.UpdateCourseDTO;
import com.project.StudentManagement.entity.Course;
import com.project.StudentManagement.entity.Student;
import com.project.StudentManagement.exceptions.ResourceNotFoundException;
import com.project.StudentManagement.mapper.CourseMapper;
import com.project.StudentManagement.mapper.StudentMapper;
import com.project.StudentManagement.repository.CourseRepository;
import com.project.StudentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(courseMapper::entityToDTO).collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Integer courseId) throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(courseId));
        return courseMapper.entityToDTO(course);
    }

    public List<CourseDTO> getCoursesByFee(double fee) {
        List<Course> courses = courseRepository.findByFeeLessThan(fee);
        return courses.stream().map(courseMapper::entityToDTO).collect(Collectors.toList());
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.dtoToEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.entityToDTO(savedCourse);
    }

    public List<CourseDTO> createMultipleCourses(List<CourseDTO> courseDTOs) {
        List<Course> courses = courseDTOs.stream().map(courseMapper::dtoToEntity).collect(Collectors.toList());
        List<Course> savedCourses = courseRepository.saveAll(courses);
        return savedCourses.stream().map(courseMapper::entityToDTO).collect(Collectors.toList());
    }

    public Course  updateCourse(Integer courseId, UpdateCourseDTO updateCourseDTO) throws ResourceNotFoundException {
        Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(courseId));
        courseMapper.updateCourseFromDTO(updateCourseDTO, existingCourse);
        Course savedCourse = courseRepository.save(existingCourse);
        return savedCourse;
    }

    public Map<String, Boolean> deleteCourse(Integer courseId) throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(courseId));
        courseRepository.delete(course);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Course with id " + courseId + " is deleted successfully", Boolean.TRUE);
        return response;
    }

    public Map<String, Boolean> deleteAllCourses() {
        courseRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("All courses records deleted", Boolean.TRUE);
        return response;
    }

    public Map<String, Boolean> deleteStudentFromCourse(Integer courseId, Integer studentId) throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(courseId));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));
        student.getCourses().remove(course);
        course.getStudents().remove(student);
        studentRepository.save(student);
        courseRepository.save(course);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Student is removed from course", Boolean.TRUE);
        return response;
    }

    public Set<StudentDTO> getStudentsByCourseId(Integer courseId) throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(courseId));
        Set<Student> students = course.getStudents();
        return students.stream().map(studentMapper::entityToDTO).collect(Collectors.toSet());
    }
}
