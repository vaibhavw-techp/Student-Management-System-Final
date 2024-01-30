package com.project.StudentManagement.services;

import com.project.StudentManagement.dto.StudentDTO;
import com.project.StudentManagement.entity.Course;
import com.project.StudentManagement.entity.Student;
import com.project.StudentManagement.exceptions.ResourceNotFoundException;
import com.project.StudentManagement.repository.CourseRepository;
import com.project.StudentManagement.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Integer studentId) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));
        return convertToDTO(student);
    }

    public List<StudentDTO> getStudentsByName(String name) {
        List<Student> students = studentRepository.findByNameContaining(name);
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    public List<StudentDTO> createMultipleStudents(List<StudentDTO> studentDTOs) {
        List<Student> students = studentDTOs.stream().map(this::convertToEntity).collect(Collectors.toList());
        List<Student> savedStudents = studentRepository.saveAll(students);
        return savedStudents.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ResponseEntity<StudentDTO> assignStudentToCourse(Integer studentId, Integer courseId) throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(courseId));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));
        student.getCourses().add(course);
        course.getStudents().add(student);
        courseRepository.save(course);
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(convertToDTO(savedStudent));
    }

    public ResponseEntity<StudentDTO> updateStudent(Integer studentId, StudentDTO studentDTO) throws ResourceNotFoundException {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));
        Student updatedStudent = convertToEntity(studentDTO);
        updatedStudent.setId(existingStudent.getId());
        Student savedStudent = studentRepository.save(updatedStudent);
        return ResponseEntity.ok(convertToDTO(savedStudent));
    }

    public Map<String, Boolean> deleteStudent(Integer studentId) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));
        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Student with id " + studentId + " is deleted successfully", Boolean.TRUE);
        return response;
    }

    public Map<String, Boolean> deleteAllStudents() {
        studentRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("All Students records deleted", Boolean.TRUE);
        return response;
    }

    private StudentDTO convertToDTO(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }
}
