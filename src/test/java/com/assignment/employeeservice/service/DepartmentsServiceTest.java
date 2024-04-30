package com.assignment.employeeservice.service;

import com.assignment.employeeservice.dto.DepartmentsRequestDto;
import com.assignment.employeeservice.dto.DepartmentsResponseDto;
import com.assignment.employeeservice.dto.DepartmentsUpdateRequestDto;
import com.assignment.employeeservice.dto.EmployeesResponseDto;
import com.assignment.employeeservice.entity.Departments;
import com.assignment.employeeservice.entity.Employees;
import com.assignment.employeeservice.repository.DepartmentsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class DepartmentsServiceTest {

    @InjectMocks
    DepartmentsService departmentsService;

    @Mock
    DepartmentsRepository departmentsRepository;

    DepartmentsRequestDto departmentsRequestDto = DepartmentsRequestDto.builder()
            .departmentName("First Department Name")
            .deptNo("1A")
            .build();

    DepartmentsUpdateRequestDto departmentsUpdateRequestDto = DepartmentsUpdateRequestDto.builder()
            .departmentName("Updated Department Name")
            .build();

    Departments departments = Departments.builder()
            .deptNo("1A")
            .deptName("Updated Department Name")
            .build();

    @Test
    public void createDepartmentsTest() {
        Mockito.when(departmentsRepository.save(Mockito.any())).thenReturn(departments);

        DepartmentsResponseDto responseDto = DepartmentsResponseDto.builder()
                .deptNo("1A")
                .departmentName("First Department Name")
                .build();
        Assertions.assertEquals(responseDto.getDepartmentName(), departmentsService.createDepartment(departmentsRequestDto).getDepartmentName());
    }

    @Test
    public void getDepartmentsTest() {
        Mockito.when(departmentsRepository.findAll()).thenReturn(List.of(Mockito.mock(Departments.class)));
        Assertions.assertNotEquals(0,departmentsService.getAllDepartments().size());
    }

    @Test
    public void getDepartmentsWithZeroDataTest() {
        Mockito.when(departmentsRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(0,departmentsService.getAllDepartments().size());
    }

    @Test
    public void getDepartmentsByDeptNoTest() {
        Mockito.when(departmentsRepository.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(Mockito.mock(Departments.class)));
        Assertions.assertNotNull(departmentsService.getDepartmentsByDeptNo(Mockito.anyString()));
    }

    @Test
    public void getDepartmentsByDeptNoWithZeroDataTest() {
        Mockito.when(departmentsRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () ->departmentsService.getDepartmentsByDeptNo(Mockito.anyString()));
    }

    @Test
    public void updateDepartmentsTest() {
        Mockito.when(departmentsRepository.findById(Mockito.anyString())).thenReturn(Optional.of(departments));
        Assertions.assertEquals(departmentsUpdateRequestDto.getDepartmentName(),
                departmentsService.updateDepartments(departments.getDeptNo(), departmentsUpdateRequestDto).getDepartmentName());
    }

    @Test
    public void updateDepartmentsWithIdNotFoundTest() {
        Mockito.when(departmentsRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                departmentsService.updateDepartments(departments.getDeptNo(), departmentsUpdateRequestDto));
    }

    @Test
    public void deleteEmployeeTest() {
        Mockito.when(departmentsRepository.findById(Mockito.anyString())).thenReturn(Optional.of(departments));
        Assertions.assertDoesNotThrow(()-> departmentsService.deleteDepartments(departments.getDeptNo()));
    }

    @Test
    public void deleteEmployeeWithIdNotFoundTest() {
        Mockito.when(departmentsRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> departmentsService.deleteDepartments(departments.getDeptNo()));
    }

}
