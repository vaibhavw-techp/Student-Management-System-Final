package com.project.StudentManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ADDRESS_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String locality;
    private String city;
    private Integer pincode;

    @ManyToOne
    //@JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

}
