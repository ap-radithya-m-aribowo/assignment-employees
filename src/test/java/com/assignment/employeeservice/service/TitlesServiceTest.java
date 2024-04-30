package com.assignment.employeeservice.service;

import com.assignment.employeeservice.dto.EmployeesResponseDto;
import com.assignment.employeeservice.dto.TitlesRequestDto;
import com.assignment.employeeservice.dto.TitlesResponseDto;
import com.assignment.employeeservice.dto.TitlesUpdateRequestDto;
import com.assignment.employeeservice.entity.Employees;
import com.assignment.employeeservice.entity.Titles;
import com.assignment.employeeservice.repository.EmployeesRepository;
import com.assignment.employeeservice.repository.TitlesRepository;
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
public class TitlesServiceTest {

    @InjectMocks
    private TitlesService titlesService;

    @Mock
    TitlesRepository titlesRepository;

    @Mock
    EmployeesRepository employeesRepository;

    private TitlesRequestDto titlesRequestDto = TitlesRequestDto.builder()
            .empNo(1)
            .title("title")
            .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .fromDate(LocalDateTime.of(2023, 12, 01, 00, 00))
            .build();

    private TitlesUpdateRequestDto titlesUpdateRequestDto = TitlesUpdateRequestDto.builder()
            .title("updated title")
            .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .fromDate(LocalDateTime.of(2023, 12, 01, 00, 00))
            .build();

    private Titles titles = Titles.builder()
            .empNo(1)
            .title("title")
            .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .fromDate(LocalDateTime.of(2023, 12, 01, 00, 00))
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
    public void createTitlesTest() {
        Mockito.when(titlesRepository.save(Mockito.any())).thenReturn(titles);
        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employees));

            TitlesResponseDto responseDto = TitlesResponseDto.builder()
                    .employees(Mockito.mock(Employees.class))
                    .title("title")
                    .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
                    .fromDate(LocalDateTime.of(2023, 12, 01, 00, 00))
                .build();
        Assertions.assertEquals(responseDto.getTitle(), titlesService.createTitles(titlesRequestDto).getTitle());
    }

    @Test
    public void getTitlesTest() {
        Mockito.when(titlesRepository.findAll()).thenReturn(List.of(Mockito.mock(Titles.class)));
        Assertions.assertNotEquals(0,titlesService.getAllTitles().size());
    }

    @Test
    public void getTitlesWithZeroDataTest() {
        Mockito.when(titlesRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(0,titlesService.getAllTitles().size());
    }

    @Test
    public void getTitlesByEmpNoTest() {
        Mockito.when(titlesRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(Mockito.mock(Titles.class)));
        Assertions.assertNotNull(titlesService.getTitlesByEmpNo(Mockito.anyInt()));
    }

    @Test
    public void getTitlesByEmpNoWithZeroDataTest() {
        Mockito.when(titlesRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () ->titlesService.getTitlesByEmpNo(Mockito.anyInt()));
    }

    @Test
    public void updateTitlesTest() {
        Mockito.when(titlesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(titles));
        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employees));

        Assertions.assertEquals(titlesUpdateRequestDto.getTitle(),
                titlesService.updateTitles(titles.getEmpNo(), titlesUpdateRequestDto).getTitle());
    }

    @Test
    public void updateTitlesWithIdNotFoundTest() {
        Mockito.when(titlesRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> titlesService.updateTitles(titles.getEmpNo(), titlesUpdateRequestDto));
    }

    @Test
    public void deleteTitlesTest() {
        Mockito.when(titlesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(titles));
        Assertions.assertDoesNotThrow(()-> titlesService.deleteTitles(titles.getEmpNo()));
    }

    @Test
    public void deleteTitlesWithIdNotFoundTest() {
        Mockito.when(titlesRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> titlesService.deleteTitles(titles.getEmpNo()));
    }

}
