package com.project.StudentManagement.controller;

import com.project.StudentManagement.dto.CourseDTO;
import com.project.StudentManagement.dto.StudentDTO;
import com.project.StudentManagement.dto.UpdateCourseDTO;
import com.project.StudentManagement.entity.Course;
import com.project.StudentManagement.exceptions.ResourceNotFoundException;
import com.project.StudentManagement.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/Courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable(value = "id") Integer courseId) throws ResourceNotFoundException {
        CourseDTO course = courseService.getCourseById(courseId);
        return ResponseEntity.ok().body(course);
    }

    @GetMapping("/getCourse/fees/{fee}")
    public List<CourseDTO> getCoursesByFees(@PathVariable double fee) {
        return courseService.getCoursesByFee(fee);
    }



    @PostMapping("/course")

    public CourseDTO createCourses(@RequestBody CourseDTO courseDTO) {
        return courseService.createCourse(courseDTO);
    }

    @PostMapping
    public List<CourseDTO> createMultipleCourses(@Valid @RequestBody List<CourseDTO> courseDTOs) {
        return courseService.createMultipleCourses(courseDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourses(@PathVariable(value = "id") Integer courseId,
                                                   @Valid @RequestBody UpdateCourseDTO updateCourseDTO) throws ResourceNotFoundException {
        Course updatedCourse = courseService.updateCourse(courseId, updateCourseDTO);
        return ResponseEntity.ok(updatedCourse);
    }


    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCourses(@PathVariable(value = "id") Integer courseId) throws ResourceNotFoundException {
        return courseService.deleteCourse(courseId);
    }

    @DeleteMapping
    public Map<String, Boolean> deleteAllCourses() {
        return courseService.deleteAllCourses();
    }

    @DeleteMapping("/{cid}/student/{sid}")
    public Map<String, Boolean> deleteStudentFromCourse(@PathVariable(value = "cid") Integer courseId,
                                                        @PathVariable(value = "sid") Integer studentId) throws ResourceNotFoundException {
        return courseService.deleteStudentFromCourse(courseId, studentId);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<StudentDTO>> getStudentsByCourseId(@PathVariable(value = "id") Integer courseId)
            throws ResourceNotFoundException {
        Set<StudentDTO> students = courseService.getStudentsByCourseId(courseId);
        return ResponseEntity.ok(students);
    }
}
