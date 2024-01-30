package com.project.StudentManagement.requestforpost;

import java.util.List;

public class StudentRequest {

    private String name;
    private String year;
    private String dept;
    private List<CourseRequest> courses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public List<CourseRequest> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseRequest> courses) {
        this.courses = courses;
    }
}

