package com.project.StudentManagement.services;

import com.project.StudentManagement.dto.AddressDTO;
import com.project.StudentManagement.dto.StudentAddressDTO;
import com.project.StudentManagement.entity.Address;
import com.project.StudentManagement.entity.Student;
import com.project.StudentManagement.exceptions.ResourceNotFoundException;
import com.project.StudentManagement.mapper.AddressMapper;
import com.project.StudentManagement.repository.AddressRepository;
import com.project.StudentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper; // Assuming you have an AddressMapper
    public ResponseEntity<?> AssignAddressesToAddressService(Integer studentId, List<StudentAddressDTO> addresses, Student student) throws ResourceNotFoundException {
        List<Address> temp_list = new ArrayList<>();
        List<Address> Temp_Addresses = addressRepository.findByStudentId(studentId);

        for (StudentAddressDTO studentAddressDTO : addresses) {
            List<AddressDTO> addressDTOList = studentAddressDTO.getAddresses();

            if(addressDTOList == null) return null;

            for (AddressDTO addressDTO : addressDTOList) {
                if (addressDTO.getId() == null) {
                    temp_list.add(addressMapper.dtoToEntity(addressDTO));
                } else {
                    boolean checkIfPresent = false;
                    Address existingAddress = null;

                    // Check if the Address ID exists for the given student
                    for (Address tempAddress : Temp_Addresses) {
                        if (tempAddress.getId().equals(addressDTO.getId())) {
                            existingAddress = addressRepository.findById(addressDTO.getId())
                                    .orElseThrow(() -> new ResourceNotFoundException(addressDTO.getId()));
                            checkIfPresent = true;
                            break;
                        }
                    }

                    // If Address ID is already present, then Update
                    if (checkIfPresent) {
                        addressMapper.updateAddressFromDTO(addressDTO, existingAddress);
                        temp_list.add(existingAddress);
                    } else {
                        return ResponseEntity.badRequest()
                                .body("Address ID " + addressDTO.getId() + " does not exist for the student");
                    }
                }
            }
        }

        // Associate addresses with the student
        for (Address address : temp_list) {
            address.setStudent(student);
        }

        // Add addresses to the student's list of addresses
        student.getAddresses().addAll(temp_list);

        // Save the student entity along with the addresses
        studentRepository.save(student);

        return ResponseEntity.ok(temp_list);
    }
}


