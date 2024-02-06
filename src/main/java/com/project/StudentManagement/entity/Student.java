package com.project.StudentManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.StudentManagement.entity.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Entity
@Table(name = "STUDENT_TBL")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_tbl_seq", allocationSize = 1)
    private Integer id;

//    @Column(name="name")
//    @NotEmpty
//    private String name;

    private String firstName;
    private String lastName;

    @Column(name = "academic_year")
//    @NotNull
    private String year;

    @Column(name="department")
    @NotNull
    private String dept;
    //    @Column(name = "Courses")

    @ManyToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course_table",
            joinColumns = {
                    @JoinColumn(name = "student_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id")
            })

//    @JsonManagedReference
    private Set<Course> courses;

}
