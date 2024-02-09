package com.project.StudentManagement.repository;


import com.project.StudentManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

//    List<Student> findByNameContaining(String name);
}
