package com.assignment.employeeservice.service;

import com.assignment.employeeservice.dto.*;
import com.assignment.employeeservice.entity.Employees;
import com.assignment.employeeservice.entity.Salaries;
import com.assignment.employeeservice.repository.EmployeesRepository;
import com.assignment.employeeservice.repository.SalariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalariesService {

    @Autowired
    SalariesRepository salariesRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    public SalariesResponseDto createSalaries(SalariesRequestDto requestDto) {
        Salaries salaries = new Salaries();
        salaries.setEmpNo(requestDto.getEmpNo());
        salaries.setSalary(requestDto.getSalary());
        salariesRepository.saveAndFlush(salaries);

        return getSalariesResponseDto(salaries, requestDto.getEmpNo());
    }

    public List<Salaries> getAllSalaries() {
        return salariesRepository.findAll();
    }

    public Salaries getSalariesByEmpNo(Integer empNo) {
        Optional<Salaries> salaries = salariesRepository.findById(empNo);

        if (!salaries.isPresent()) {
            throw new IllegalArgumentException("Salaries not found");
        }

        return salaries.get();
    }

    public SalariesResponseDto updateSalaries(Integer empNo, SalariesUpdateRequestDto salariesUpdateRequestDto) throws IllegalArgumentException{
        Optional<Salaries> salaries = salariesRepository.findById(empNo);

        if (!salaries.isPresent()) {
            throw new IllegalArgumentException("Salaries not found");
        }

        Salaries salariesEntity = salaries.get();
        salariesEntity.setSalary(salariesUpdateRequestDto.getSalary());
        salariesEntity.setFromDate(salariesUpdateRequestDto.getFromDate());
        salariesEntity.setToDate(salariesUpdateRequestDto.getToDate());
        salariesRepository.save(salariesEntity);

        return getSalariesResponseDto(salariesEntity, salariesEntity.getEmpNo());
    }

    private SalariesResponseDto getSalariesResponseDto(Salaries salaries, Integer empNo) {
        Employees employees = employeesRepository.findById(empNo).get();

        SalariesResponseDto responseDto = new SalariesResponseDto();
        responseDto.setEmployees(employees);
        responseDto.setSalary(salaries.getSalary());
        responseDto.setFromDate(salaries.getFromDate());
        responseDto.setToDate(salaries.getToDate());
        return responseDto;
    }


    public void deleteSalaries(Integer empNo) throws IllegalArgumentException{
        Optional<Salaries> salaries = salariesRepository.findById(empNo);

        if (!salaries.isPresent()) {
            throw new IllegalArgumentException("Salaries not found");
        }
        salariesRepository.delete(salaries.get());
    }

}
