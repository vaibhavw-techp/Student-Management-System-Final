package com.project.StudentManagement.controller;

import com.project.StudentManagement.dto.AddressDTO;
import com.project.StudentManagement.dto.StudentAddressDTO;
import com.project.StudentManagement.dto.StudentDTO;
import com.project.StudentManagement.dto.UpdateStudentDTO;
import com.project.StudentManagement.entity.Address;
import com.project.StudentManagement.entity.Student;
import com.project.StudentManagement.exceptions.ResourceNotFoundException;
import com.project.StudentManagement.mapper.AddressMapper;
import com.project.StudentManagement.repository.AddressRepository;
import com.project.StudentManagement.repository.StudentRepository;
import com.project.StudentManagement.services.AddressService;
import com.project.StudentManagement.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable(value = "id") Integer studentId) throws ResourceNotFoundException {
        StudentDTO student = studentService.getStudentById(studentId);
        return ResponseEntity.ok().body(student);
    }

//    @GetMapping("/name/{name}")
//    public List<StudentDTO> findByName(@PathVariable String name) {
//        return studentService.getStudentsByName(name);
//    }

    @PostMapping("/student")
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @PostMapping
    public List<StudentDTO> createMultipleStudents(@RequestBody List<StudentDTO> studentDTOs) {
        return studentService.createMultipleStudents(studentDTOs);
    }
    // To assign student with multiple Courses already present in database
    @PostMapping("/{studentId}/courses")
    public ResponseEntity<String> assignCoursesToStudent(
            @PathVariable("studentId") Integer studentId,
            @RequestBody List<Integer> courseIds) {

        try {
            studentService.assignCoursesToStudent(studentId, courseIds);
            return ResponseEntity.ok("Courses assigned to student successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{sid}/course/{cid}")
    public ResponseEntity<StudentDTO> assignStudentToCourse(@PathVariable(value = "sid") Integer studentId,
                                                            @PathVariable(value = "cid") Integer courseId) throws ResourceNotFoundException {
        return studentService.assignStudentToCourse(studentId, courseId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer studentId,
                                                 @Validated @RequestBody UpdateStudentDTO updateStudentDTO) throws ResourceNotFoundException {
        return studentService.updateStudent(studentId, updateStudentDTO);
    }


    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Integer studentId) throws ResourceNotFoundException {
        return studentService.deleteStudent(studentId);
    }

    @DeleteMapping
    public Map<String, Boolean> deleteAllStudents() {
        return studentService.deleteAllStudents();
    }

//    @PutMapping("/{studentId}/addresses")
//    public ResponseEntity<?> AssignAddressesToStudent(
//            @PathVariable("studentId") Integer studentId,
//            @RequestBody List<AddressDTO> addresses) throws ResourceNotFoundException {
//        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));
//
//        return studentService.AssignAddressesToStudentService(studentId,addresses,student);
//    }

    @PostMapping("/{studentId}/addresses")
    public StudentAddressDTO AssignAddressesToStudent(
            @PathVariable("studentId") Integer studentId,
            @RequestBody StudentAddressDTO studentAddressDTO) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));

        return studentService.updateStudentWithAddress(studentId,studentAddressDTO);
    }

}
