package com.project.StudentManagement.controller;

import com.project.StudentManagement.dto.AddressDTO;
import com.project.StudentManagement.dto.StudentAddressDTO;
import com.project.StudentManagement.entity.Address;
import com.project.StudentManagement.entity.Student;
import com.project.StudentManagement.exceptions.ResourceNotFoundException;
import com.project.StudentManagement.repository.StudentRepository;
import com.project.StudentManagement.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private StudentRepository studentRepository; // Assuming you have a StudentRepository defined

    @Autowired
    private AddressService addressService;

    @GetMapping("/students/{studentId}")
    public List<Address> getAddressesByStudentId(@PathVariable Integer studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            // Handle student not found
            return null;
        }
        return student.getAddresses();
    }

    @PostMapping("/students/{studentId}/addresses")
    public ResponseEntity<?> AssignAddressesToStudent(
            @PathVariable("studentId") Integer studentId,
            @RequestBody List<StudentAddressDTO> studentAddressDTOs) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));

        return addressService.AssignAddressesToAddressService(studentId,studentAddressDTOs,student);
    }
}