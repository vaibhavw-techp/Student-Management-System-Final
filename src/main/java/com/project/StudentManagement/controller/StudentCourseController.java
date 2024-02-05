package com.project.StudentManagement.controller;

import com.project.StudentManagement.dto.CourseDTO;
import com.project.StudentManagement.dto.StudentDTO;
import com.project.StudentManagement.entity.Course;
import com.project.StudentManagement.entity.Student;
import com.project.StudentManagement.repository.CourseRepository;
import com.project.StudentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/studentcourse")
public class StudentCourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<String> createNewStudent(@Valid @RequestBody StudentDTO studentDTO) {
        String studentName = studentDTO.getFullName();
        String studentFirstName = studentDTO.getFirstName();
        String studentLastName = studentDTO.getLastName();
        String studentYear = studentDTO.getYear();
        String studentDept = studentDTO.getDept();

        Student student = new Student();
        student.setFirstName(studentFirstName);
        student.setLastName(studentLastName);
        student.setYear(studentYear);
        student.setDept(studentDept);

        // Save the student to the database
        studentRepository.save(student);

        Set<CourseDTO> courseDTOs = studentDTO.getCourses();
        for (CourseDTO courseDTO : courseDTOs) {
            Course course = new Course();
            course.setTitle(courseDTO.getTitle());
            course.setCourseCode(courseDTO.getCourseCode());
            course.setCredits(courseDTO.getCredits());
            course.setFee(courseDTO.getFee());

            // Save the course
            course = courseRepository.save(course);

            // Update bidirectional relationship
            if (student.getCourses() == null) {
                student.setCourses(new HashSet<>());
            }
            student.getCourses().add(course);
            course.getStudents().add(student);

            // Save changes once after modifications
            courseRepository.save(course);
            studentRepository.save(student);
        }

        return ResponseEntity.ok("Student created successfully");
    }
}
