package com.project.StudentManagement.requestforpost;

public class CourseRequest {
        private String title;
        private Double courseCode;
        private Integer credits;
        private Double fee;

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public Double getCourseCode() {
                return courseCode;
        }

        public void setCourseCode(Double courseCode) {
                this.courseCode = courseCode;
        }

        public Integer getCredits() {
                return credits;
        }

        public void setCredits(Integer credits) {
                this.credits = credits;
        }

        public Double getFee() {
                return fee;
        }

        public void setFee(Double fee) {
                this.fee = fee;
        }
}

