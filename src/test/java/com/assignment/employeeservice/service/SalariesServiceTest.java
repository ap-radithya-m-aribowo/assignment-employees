package com.assignment.employeeservice.service;

import com.assignment.employeeservice.dto.SalariesRequestDto;
import com.assignment.employeeservice.dto.SalariesResponseDto;
import com.assignment.employeeservice.dto.SalariesUpdateRequestDto;
import com.assignment.employeeservice.entity.Employees;
import com.assignment.employeeservice.entity.Salaries;
import com.assignment.employeeservice.repository.EmployeesRepository;
import com.assignment.employeeservice.repository.SalariesRepository;
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
public class SalariesServiceTest {
    
    @InjectMocks
    SalariesService salariesService;
    
    @Mock
    EmployeesRepository employeesRepository;
    
    @Mock
    SalariesRepository salariesRepository;
    
    private Salaries salaries  = Salaries.builder()
            .empNo(1)
            .salary(6000000)
            .fromDate(LocalDateTime.of(2020, 12, 01, 00, 00))
            .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .build();

    private Employees employees = Employees.builder()
            .empNo(1)
            .birthDate(LocalDateTime.of(2020, 12, 01, 00, 00))
            .firstName("John")
            .lastName("Doe")
            .gender(Employees.Gender.M)
            .hireDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .build();

    private SalariesRequestDto salariesRequestDto = SalariesRequestDto.builder()
            .empNo(1)
            .salary(6000000)
            .fromDate(LocalDateTime.of(2020, 12, 01, 00, 00))
            .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .build();

    private SalariesUpdateRequestDto salariesUpdateRequestDto = SalariesUpdateRequestDto.builder()
            .salary(6000000)
            .fromDate(LocalDateTime.of(2020, 12, 01, 00, 00))
            .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .build();

    @Test
    public void createSalariesTest() {
        Mockito.when(salariesRepository.save(Mockito.any())).thenReturn(salaries);
        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employees));

        SalariesResponseDto responseDto = SalariesResponseDto.builder()
                .employees(Mockito.mock(Employees.class))
                .salary(6000000)
                .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
                .fromDate(LocalDateTime.of(2023, 12, 01, 00, 00))
                .build();
        Assertions.assertEquals(responseDto.getSalary(), salariesService.createSalaries(salariesRequestDto).getSalary());
    }

    @Test
    public void getSalariesTest() {
        Mockito.when(salariesRepository.findAll()).thenReturn(List.of(Mockito.mock(Salaries.class)));
        Assertions.assertNotEquals(0,salariesService.getAllSalaries().size());
    }

    @Test
    public void getSalariesWithZeroDataTest() {
        Mockito.when(salariesRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(0,salariesService.getAllSalaries().size());
    }

    @Test
    public void getSalariesByEmpNoTest() {
        Mockito.when(salariesRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(Mockito.mock(Salaries.class)));
        Assertions.assertNotNull(salariesService.getSalariesByEmpNo(Mockito.anyInt()));
    }

    @Test
    public void getSalariesByEmpNoWithZeroDataTest() {
        Mockito.when(salariesRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () ->salariesService.getSalariesByEmpNo(Mockito.anyInt()));
    }

    @Test
    public void updateSalariesTest() {
        Mockito.when(salariesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(salaries));
        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employees));

        Assertions.assertEquals(salariesUpdateRequestDto.getSalary(),
                salariesService.updateSalaries(salaries.getEmpNo(), salariesUpdateRequestDto).getSalary());
    }

    @Test
    public void updateSalariesWithIdNotFoundTest() {
        Mockito.when(salariesRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> salariesService.updateSalaries(salaries.getEmpNo(), salariesUpdateRequestDto));
    }

    @Test
    public void deleteSalariesTest() {
        Mockito.when(salariesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(salaries));
        Assertions.assertDoesNotThrow(()-> salariesService.deleteSalaries(salaries.getEmpNo()));
    }

    @Test
    public void deleteSalariesWithIdNotFoundTest() {
        Mockito.when(salariesRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> salariesService.deleteSalaries(salaries.getEmpNo()));
    }
    
}
