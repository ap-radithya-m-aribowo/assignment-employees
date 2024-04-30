package com.assignment.employeeservice.service;

import com.assignment.employeeservice.dto.DepartmentsRequestDto;
import com.assignment.employeeservice.dto.DeptEmpRequestDto;
import com.assignment.employeeservice.dto.DeptEmpResponseDto;
import com.assignment.employeeservice.dto.DeptEmpUpdateRequestDto;
import com.assignment.employeeservice.entity.DeptManager;
import com.assignment.employeeservice.entity.Employees;
import com.assignment.employeeservice.entity.DeptEmp;
import com.assignment.employeeservice.repository.DeptEmpRepository;
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
public class DeptEmpServiceTest {
    
    @InjectMocks
    DeptEmpService deptEmpService;
    
    @Mock
    DeptEmpRepository deptEmpRepository;

    private DeptEmp deptEmp = DeptEmp.builder()
            .empNo(1)
            .deptNo("ACC")
            .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .fromDate(LocalDateTime.of(2023, 12, 01, 00, 00))
            .build();

    private DeptEmpRequestDto deptEmpRequestDto = DeptEmpRequestDto.builder()
            .empNo(1)
            .deptNo("ACC")
            .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
            .fromDate(LocalDateTime.of(2023, 12, 01, 00, 00))
            .build();

    private DeptEmpUpdateRequestDto deptEmpUpdateRequestDto = DeptEmpUpdateRequestDto.builder()
            .toDate(LocalDateTime.of(2003, 12, 01, 00, 00))
            .fromDate(LocalDateTime.of(2002, 12, 01, 00, 00))
            .build();


    @Test
    public void createDeptEmpTest() {
        Mockito.when(deptEmpRepository.save(Mockito.any())).thenReturn(deptEmp);

        DeptEmpResponseDto responseDto = DeptEmpResponseDto.builder()
                .empNo(1)
                .deptNo("ACC")
                .toDate(LocalDateTime.of(2024, 12, 01, 00, 00))
                .fromDate(LocalDateTime.of(2023, 12, 01, 00, 00))
                .build();
        Assertions.assertEquals(responseDto.getDeptNo(), deptEmpService.createDeptEmp(deptEmpRequestDto).getDeptNo());
        Assertions.assertEquals(responseDto.getEmpNo(), deptEmpService.createDeptEmp(deptEmpRequestDto).getEmpNo());
    }

    @Test
    public void getDeptEmpTest() {
        Mockito.when(deptEmpRepository.findAll()).thenReturn(List.of(Mockito.mock(DeptEmp.class)));
        Assertions.assertNotEquals(0,deptEmpService.getAllDeptEmp().size());
    }

    @Test
    public void getDeptEmpWithZeroDataTest() {
        Mockito.when(deptEmpRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(0,deptEmpService.getAllDeptEmp().size());
    }

    @Test
    public void getDeptEmpByEmpNoTest() {
        Mockito.when(deptEmpRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(Mockito.mock(DeptEmp.class)));
        Assertions.assertNotNull(deptEmpService.getDeptEmpByEmpNo(Mockito.anyInt()));
    }

    @Test
    public void getDeptEmpByEmpNoWithZeroDataTest() {
        Mockito.when(deptEmpRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () ->deptEmpService.getDeptEmpByEmpNo(Mockito.anyInt()));
    }

    @Test
    public void updateDeptEmpTest() {
        Mockito.when(deptEmpRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(deptEmp));

        Assertions.assertEquals(deptEmpUpdateRequestDto.getFromDate(),
                deptEmpService.updateDeptEmp(deptEmp.getEmpNo(), deptEmpUpdateRequestDto).getFromDate());
        Assertions.assertEquals(deptEmpUpdateRequestDto.getToDate(),
                deptEmpService.updateDeptEmp(deptEmp.getEmpNo(), deptEmpUpdateRequestDto).getToDate());
    }

    @Test
    public void updateDeptEmpWithIdNotFoundTest() {
        Mockito.when(deptEmpRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> deptEmpService.updateDeptEmp(deptEmp.getEmpNo(), deptEmpUpdateRequestDto));
    }

    @Test
    public void deleteDeptEmpTest() {
        Mockito.when(deptEmpRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(deptEmp));
        Assertions.assertDoesNotThrow(()-> deptEmpService.deleteDeptEmp(deptEmp.getEmpNo()));
    }

    @Test
    public void deleteDeptEmpWithIdNotFoundTest() {
        Mockito.when(deptEmpRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(IllegalArgumentException.class, () -> deptEmpService.deleteDeptEmp(deptEmp.getEmpNo()));
    }
}
