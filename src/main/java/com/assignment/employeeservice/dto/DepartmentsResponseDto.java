package com.assignment.employeeservice.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentsResponseDto {

    private String deptNo;

    private String departmentName;
}
