package com.example.demo.dto;

import com.example.demo.models.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private DepartmentDto department;

}
