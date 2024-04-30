package com.assignment.employeeservice.service;

import com.assignment.employeeservice.dto.DeptEmpResponseDto;
import com.assignment.employeeservice.dto.DeptEmpRequestDto;
import com.assignment.employeeservice.dto.DeptEmpUpdateRequestDto;
import com.assignment.employeeservice.entity.Employees;
import com.assignment.employeeservice.entity.DeptEmp;
import com.assignment.employeeservice.repository.DepartmentsRepository;
import com.assignment.employeeservice.repository.DeptEmpRepository;
import com.assignment.employeeservice.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeptEmpService {

    @Autowired
    DeptEmpRepository deptEmpRepository;
    
    @Autowired
    EmployeesRepository employeesRepository;
    
    @Autowired
    DepartmentsRepository departmentsRepository;

    public DeptEmpResponseDto createDeptEmp(DeptEmpRequestDto requestDto) {
        DeptEmp deptEmp = new DeptEmp();
        deptEmp.setEmpNo(requestDto.getEmpNo());
        deptEmp.setDeptNo(requestDto.getDeptNo());
        deptEmp.setFromDate(requestDto.getFromDate());
        deptEmp.setToDate(requestDto.getToDate());
        deptEmpRepository.saveAndFlush(deptEmp);

        return getDeptEmpResponseDto(deptEmp, requestDto.getEmpNo());
    }

    public List<DeptEmp> getAllDeptEmp() {
        return deptEmpRepository.findAll();
    }

    public DeptEmp getDeptEmpByEmpNo(Integer empNo) {
        Optional<DeptEmp> deptEmp = deptEmpRepository.findById(empNo);

        if (!deptEmp.isPresent()) {
            throw new IllegalArgumentException("DeptEmp not found");
        }

        return deptEmp.get();
    }

    public DeptEmpResponseDto updateDeptEmp(Integer empNo, DeptEmpUpdateRequestDto deptEmpUpdateRequestDto) throws IllegalArgumentException{
        Optional<DeptEmp> deptEmp = deptEmpRepository.findById(empNo);

        if (!deptEmp.isPresent()) {
            throw new IllegalArgumentException("DeptEmp not found");
        }

        DeptEmp deptEmpEntity = deptEmp.get();
        deptEmpEntity.setFromDate(deptEmpUpdateRequestDto.getFromDate());
        deptEmpEntity.setToDate(deptEmpUpdateRequestDto.getToDate());
        deptEmpRepository.save(deptEmpEntity);

        return getDeptEmpResponseDto(deptEmpEntity, deptEmpEntity.getEmpNo());
    }

    private DeptEmpResponseDto getDeptEmpResponseDto(DeptEmp deptEmp, Integer empNo) {

        DeptEmpResponseDto responseDto = new DeptEmpResponseDto();
        responseDto.setEmpNo(empNo);
        responseDto.setDeptNo(deptEmp.getDeptNo());
        responseDto.setFromDate(deptEmp.getFromDate());
        responseDto.setToDate(deptEmp.getToDate());
        return responseDto;
    }


    public void deleteDeptEmp(Integer empNo) throws IllegalArgumentException{
        Optional<DeptEmp> deptEmp = deptEmpRepository.findById(empNo);

        if (!deptEmp.isPresent()) {
            throw new IllegalArgumentException("DeptEmp not found");
        }
        deptEmpRepository.delete(deptEmp.get());
    }

}
