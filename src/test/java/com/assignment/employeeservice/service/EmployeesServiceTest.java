package com.assignment.employeeservice.service;

import com.assignment.employeeservice.dto.EmployeesRequestDto;
import com.assignment.employeeservice.dto.EmployeesResponseDto;
import com.assignment.employeeservice.dto.EmployeesUpdateRequestDto;
import com.assignment.employeeservice.entity.Employees;
import com.assignment.employeeservice.repository.EmployeesRepository;
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
public class EmployeesServiceTest {

    @InjectMocks
    private EmployeesService employeesService;

    @Mock
    EmployeesRepository employeesRepository;

    private EmployeesRequestDto employeesRequestDto = EmployeesRequestDto.builder()
            .empNo(1)
            .birthDate(LocalDateTime.of(2020, 12, 01, 00, 00))
            .firstName("John")
            .lastName("Doe")
            .gender(Employees.Gender.M)
            .hireDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .build();

    private EmployeesUpdateRequestDto employeesUpdateRequestDto = EmployeesUpdateRequestDto.builder()
            .birthDate(LocalDateTime.of(2020, 12, 01, 00, 00))
            .firstName("John")
            .lastName("Mirza")
            .gender(Employees.Gender.M)
            .hireDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .build();

    private Employees employees = Employees.builder()
            .empNo(1)
            .birthDate(LocalDateTime.of(2020, 12, 01, 00, 00))
            .firstName("John")
            .lastName("Doe")
            .gender(Employees.Gender.M)
            .hireDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .build();

    @Test
    public void createEmployeeTest() {
        Mockito.when(employeesRepository.save(Mockito.any())).thenReturn(employees);

        EmployeesResponseDto responseDto = EmployeesResponseDto.builder()
                .empNo(1)
                .birthDate(LocalDateTime.of(2020, 12, 01, 00, 00))
                .firstName("John")
                .lastName("Doe")
                .gender(Employees.Gender.M)
                .hireDate(LocalDateTime.of(2024, 12, 01, 00, 00))
                .build();
        Assertions.assertEquals(responseDto.getEmpNo(), employeesService.createEmployees(employeesRequestDto).getEmpNo());
    }

    @Test
    public void getEmployeesTest() {
        Mockito.when(employeesRepository.findAll()).thenReturn(List.of(Mockito.mock(Employees.class)));
        Assertions.assertNotEquals(0,employeesService.getAllEmployees().size());
    }

    @Test
    public void getEmployeesWithZeroDataTest() {
        Mockito.when(employeesRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(0,employeesRepository.findAll().size());
    }

    @Test
    public void updateEmployeeTest() {
        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employees));
        Assertions.assertEquals(employeesUpdateRequestDto.getLastName(),
                employeesService.updateEmployees(employees.getEmpNo(), employeesUpdateRequestDto).getLastName());
    }

    @Test
    public void updateEmployeeWithIdNotFoundTest() {
        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> employeesService.updateEmployees(employees.getEmpNo(), employeesUpdateRequestDto));
    }

    @Test
    public void deleteEmployeeTest() {
        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employees));
        Assertions.assertDoesNotThrow(()-> employeesService.deleteEmployees(employees.getEmpNo()));
    }

    @Test
    public void deleteEmployeeWithIdNotFoundTest() {
        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> employeesService.deleteEmployees(employees.getEmpNo()));
    }

}
