package com.project.StudentManagement.services;

import com.project.StudentManagement.dto.*;
import com.project.StudentManagement.entity.Address;
import com.project.StudentManagement.entity.Course;
import com.project.StudentManagement.entity.Student;
import com.project.StudentManagement.exceptions.ResourceNotFoundException;
import com.project.StudentManagement.mapper.AddressMapper;
import com.project.StudentManagement.mapper.StudentMapper;
import com.project.StudentManagement.repository.AddressRepository;
import com.project.StudentManagement.repository.CourseRepository;
import com.project.StudentManagement.repository.StudentRepository;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(studentMapper::entityToDTO).collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Integer studentId) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));
        return convertToDTO(student);
    }

//    public List<StudentDTO> getStudentsByName(String name) {
//        List<Student> students = studentRepository.findByNameContaining(name);
//        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
//    }

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

    // Assign Courses to Particular Student
    public void assignCoursesToStudent(Integer studentId, List<Integer> courseIds) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));

        List<Course> validCourses = new ArrayList<>();

        for (Integer courseId : courseIds) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException(courseId);
                    });
            validCourses.add(course);
        }

        student.getCourses().addAll(validCourses);

        studentRepository.save(student);
    }

    public ResponseEntity<Student> updateStudent(Integer studentId, UpdateStudentDTO updateStudentDTO) throws ResourceNotFoundException {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));
//        Student updatedStudent = convertToEntity(studentDTO);
//        updatedStudent.setId(existingStudent.getId());
//        Student savedStudent = studentRepository.save(updatedStudent);
//        return ResponseEntity.ok(convertToDTO(savedStudent));

        studentMapper.updateStudentFromDTO(updateStudentDTO, existingStudent);
        Student savedStudent = studentRepository.save(existingStudent);
        return ResponseEntity.ok(savedStudent);
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


    // Address Related Services:
    public StudentAddressDTO updateStudentWithAddress(Integer id, StudentAddressDTO studentAddressDTO) throws ResourceNotFoundException{

        Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        List<Address> addressList = addressMapper.getEntityList(studentAddressDTO.getAddresses());
        int flag = 0;

        if(addressList != null){
            for(Address address: addressList){
                flag = 0;

                for(Address address1 : student.getAddresses()){
                    if(address1.getId().equals(address.getId())){
                        flag = 1;
                        break;
                    }
                }
                if(address.getId() != null && flag == 0){
                    Integer errorId = address.getId();
                    throw new ResourceNotFoundException(errorId); // For NNNNNNNNNNNNNNNNNNNNNNOOOOOOOOOOOOOOOOOOOOOWWWWWWWWWWWWWWWWW
                }
            }
        }

        studentMapper.updateStudentData(studentAddressDTO, student);
        Student studentReturn = studentRepository.save(student);
        StudentAddressDTO temp  = new StudentAddressDTO();
        studentMapper.convertEntityToStudentAddressDTO(temp, studentReturn);
        return temp;
    }


    private StudentDTO convertToDTO(Student student) {
        return studentMapper.entityToDTO(student);
    }

    private Student convertToEntity(StudentDTO studentDTO) {
        return studentMapper.dtoToEntity(studentDTO);
    }
}
