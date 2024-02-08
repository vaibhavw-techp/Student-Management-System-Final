package com.project.StudentManagement.repository;

import com.project.StudentManagement.dto.AddressDTO;
import com.project.StudentManagement.entity.Address;
import com.project.StudentManagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {

    List<Address> findByStudentId(Integer studentId);
}
