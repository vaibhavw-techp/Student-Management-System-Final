package com.project.StudentManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COURSE_TBL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_tbl_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "title", nullable = false)
    @NotEmpty
    private String title;
    @NotEmpty
    @Column(name = "course_code", nullable = false)
    @NotEmpty
    private double courseCode;

    @Column(name = "credits", nullable = false)
    @NotEmpty
    private int credits;

    @Column(name = "fee",nullable = false)
    private double fee;
    //    @Column(name = "Students")


    @ManyToMany( fetch = FetchType.LAZY,  mappedBy = "courses")
//    @JsonBackReference
    private Set<Student> students = new HashSet<>(); // Initialize the set

}
