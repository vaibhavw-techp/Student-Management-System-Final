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
    private String firstName;
    private String lastName;
    List<AddressDTO> addresses;
}
