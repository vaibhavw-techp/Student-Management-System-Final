package com.project.StudentManagement.controller;


import com.project.StudentManagement.entity.Course;
import com.project.StudentManagement.entity.Student;
import com.project.StudentManagement.repository.CourseRepository;
import com.project.StudentManagement.repository.StudentRepository;
import com.project.StudentManagement.requestforpost.CourseRequest;
import com.project.StudentManagement.requestforpost.StudentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/studentcourse")
public class StudentCourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<String> createNewStudent(@Valid @RequestBody StudentRequest studentRequest) {
        String studentName = studentRequest.getName();
        String studentYear = studentRequest.getYear();
        String studentDept = studentRequest.getDept();
        List<CourseRequest> courseRequests = studentRequest.getCourses();

        Student student = new Student();
        student.setName(studentName);
        student.setYear(studentYear);
        student.setDept(studentDept);

        // Save the student to the database
        studentRepository.save(student);

        for (CourseRequest courseRequest : courseRequests) {
            Course course = new Course();
            course.setTitle(courseRequest.getTitle());
            course.setCourseCode(courseRequest.getCourseCode());
            course.setCredits(courseRequest.getCredits());
            course.setFee(courseRequest.getFee());

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
