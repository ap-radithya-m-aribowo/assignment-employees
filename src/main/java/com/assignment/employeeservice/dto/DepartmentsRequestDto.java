package com.assignment.employeeservice.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentsRequestDto {

    @NonNull
    private String deptNo;

    @NonNull
    private String departmentName;
}
