package com.assignment.employeeservice.service;

import com.assignment.employeeservice.dto.DepartmentsRequestDto;
import com.assignment.employeeservice.dto.DepartmentsResponseDto;
import com.assignment.employeeservice.dto.DepartmentsUpdateRequestDto;
import com.assignment.employeeservice.entity.Departments;
import com.assignment.employeeservice.repository.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsService {

    @Autowired
    private DepartmentsRepository departmentsRepository;

    public DepartmentsResponseDto createDepartment(DepartmentsRequestDto departmentsRequestDto) {
        Departments departments = new Departments();
        departments.setDeptNo(departmentsRequestDto.getDeptNo());
        departments.setDeptName(departmentsRequestDto.getDepartmentName());
        departmentsRepository.saveAndFlush(departments);

        DepartmentsResponseDto departmentsResponseDto = new DepartmentsResponseDto();
        departmentsResponseDto.setDeptNo(departments.getDeptNo());
        departmentsResponseDto.setDepartmentName(departments.getDeptName());
        return departmentsResponseDto;
    }

    public List<Departments> getAllDepartments() {
        return departmentsRepository.findAll();
    }

    public DepartmentsResponseDto updateDepartments(String deptNo, DepartmentsUpdateRequestDto departmentsRequestDto) throws IllegalArgumentException{
        Optional<Departments> departments = departmentsRepository.findById(deptNo);

        if (!departments.isPresent()) {
            throw new IllegalArgumentException("Department not found");
        }

        Departments departmentsEntity = departments.get();
        departmentsEntity.setDeptName(departmentsRequestDto.getDepartmentName());
        departmentsRepository.save(departmentsEntity);

        DepartmentsResponseDto departmentsResponseDto = new DepartmentsResponseDto();
        departmentsResponseDto.setDeptNo(departmentsEntity.getDeptNo());
        departmentsResponseDto.setDepartmentName(departmentsEntity.getDeptName());
        return departmentsResponseDto;
    }


    public void deleteDepartments(String deptNo) throws IllegalArgumentException{
        Optional<Departments> departments = departmentsRepository.findById(deptNo);

        if (!departments.isPresent()) {
            throw new IllegalArgumentException("Department not found");
        }
        departmentsRepository.delete(departments.get());
    }
}
