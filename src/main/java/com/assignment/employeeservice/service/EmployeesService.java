package com.assignment.employeeservice.service;

import com.assignment.employeeservice.dto.*;
import com.assignment.employeeservice.entity.Departments;
import com.assignment.employeeservice.entity.Employees;
import com.assignment.employeeservice.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository employeesRepository;

    public EmployeesResponseDto createEmployees(EmployeesRequestDto employeesRequestDto) {
        Employees employees = new Employees();
        employees.setEmpNo(employeesRequestDto.getEmpNo());
        employees.setBirthDate(employeesRequestDto.getBirthDate());
        employees.setFirstName(employeesRequestDto.getFirstName());
        employees.setLastName(employeesRequestDto.getLastName());
        employees.setGender(employeesRequestDto.getGender());
        employees.setHireDate(employeesRequestDto.getHireDate());
        employeesRepository.saveAndFlush(employees);

        EmployeesResponseDto employeesResponseDto = new EmployeesResponseDto();
        employeesResponseDto.setEmpNo(employees.getEmpNo());
        employeesResponseDto.setBirthDate(employees.getBirthDate());
        employeesResponseDto.setFirstName(employees.getFirstName());
        employeesResponseDto.setLastName(employees.getLastName());
        employeesResponseDto.setGender(employees.getGender());
        employeesResponseDto.setHireDate(employees.getHireDate());
        return employeesResponseDto;
    }

    public List<Employees> getAllDepartments() {
        return employeesRepository.findAll();
    }

    public EmployeesResponseDto updateEmployees(Integer empNo, EmployeesUpdateRequestDto employeesUpdateRequestDto) throws IllegalArgumentException{
        Optional<Employees> employees = employeesRepository.findById(empNo);

        if (!employees.isPresent()) {
            throw new IllegalArgumentException("Department not found");
        }

        Employees employeesEntity = employees.get();
        employeesEntity.setBirthDate(employeesUpdateRequestDto.getBirthDate());
        employeesEntity.setFirstName(employeesUpdateRequestDto.getFirstName());
        employeesEntity.setLastName(employeesUpdateRequestDto.getLastName());
        employeesEntity.setGender(employeesUpdateRequestDto.getGender());
        employeesEntity.setHireDate(employeesUpdateRequestDto.getHireDate());
        employeesRepository.save(employeesEntity);

        EmployeesResponseDto employeesResponseDto = new EmployeesResponseDto();
        employeesResponseDto.setBirthDate(employeesEntity.getBirthDate());
        employeesResponseDto.setFirstName(employeesEntity.getFirstName());
        employeesResponseDto.setLastName(employeesEntity.getLastName());
        employeesResponseDto.setGender(employeesEntity.getGender());
        employeesResponseDto.setHireDate(employeesEntity.getHireDate());
        return employeesResponseDto;
    }


    public void deleteEmployees(Integer empNo) throws IllegalArgumentException{
        Optional<Employees> employees = employeesRepository.findById(empNo);

        if (!employees.isPresent()) {
            throw new IllegalArgumentException("Employees not found");
        }
        employeesRepository.delete(employees.get());
    }

}
