package com.assignment.employeeservice.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentsUpdateRequestDto {

    private String departmentName;
}
