package com.project.StudentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAddressDTO {
    private String fullName;
    private String year;
    private String dept;
    List<AddressDTO> addresses;
}
