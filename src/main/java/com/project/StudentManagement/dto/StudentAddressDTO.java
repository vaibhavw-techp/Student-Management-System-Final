package com.project.StudentManagement.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private Integer id;
    private String firstName;
    private String lastName;
    List<AddressDTO> addresses;
}
