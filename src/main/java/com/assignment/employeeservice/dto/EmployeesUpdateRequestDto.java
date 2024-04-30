package com.assignment.employeeservice.dto;

import com.assignment.employeeservice.entity.Employees;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesUpdateRequestDto {

    private LocalDateTime birthDate;

    private String firstName;

    private String lastName;

    private Employees.Gender gender;

    private LocalDateTime hireDate;
}
