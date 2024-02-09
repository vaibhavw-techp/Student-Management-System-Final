package com.project.StudentManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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

    @ManyToMany( fetch = FetchType.LAZY, targetEntity = Course.class, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course_table",
            joinColumns = {
                    @JoinColumn(name = "student_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id")
            })
    private Set<Course> courses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();
//    @JsonManagedReference


}
