package com.assignment.employeeservice.service;

import com.assignment.employeeservice.dto.*;
import com.assignment.employeeservice.entity.Employees;
import com.assignment.employeeservice.entity.Titles;
import com.assignment.employeeservice.repository.EmployeesRepository;
import com.assignment.employeeservice.repository.TitlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitlesService {

    @Autowired
    TitlesRepository titlesRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    public TitlesResponseDto createTitles(TitlesRequestDto requestDto) {
        Titles titles = new Titles();
        titles.setEmpNo(requestDto.getEmpNo());
        titles.setTitle(requestDto.getTitle());
        titles.setFromDate(requestDto.getFromDate());
        titles.setToDate(requestDto.getToDate());
        titlesRepository.saveAndFlush(titles);

        return getTitlesResponseDto(titles, requestDto.getEmpNo());
    }

    public List<Titles> getAllTitles() {
        return titlesRepository.findAll();
    }

    public Titles getTitlesByEmpNo(Integer empNo) {
        Optional<Titles> titles = titlesRepository.findById(empNo);

        if (!titles.isPresent()) {
            throw new IllegalArgumentException("Titles not found");
        }

        return titles.get();
    }

    public TitlesResponseDto updateTitles(Integer empNo, TitlesUpdateRequestDto titlesUpdateRequestDto) throws IllegalArgumentException{
        Optional<Titles> titles = titlesRepository.findById(empNo);

        if (!titles.isPresent()) {
            throw new IllegalArgumentException("Titles not found");
        }

        Titles titlesEntity = titles.get();
        titlesEntity.setTitle(titlesUpdateRequestDto.getTitle());
        titlesEntity.setFromDate(titlesUpdateRequestDto.getFromDate());
        titlesEntity.setToDate(titlesUpdateRequestDto.getToDate());
        titlesRepository.save(titlesEntity);

        return getTitlesResponseDto(titlesEntity, titlesEntity.getEmpNo());
    }

    private TitlesResponseDto getTitlesResponseDto(Titles titlesEntity, Integer empNo2) {
        Employees employees = employeesRepository.findById(empNo2).get();

        TitlesResponseDto responseDto = new TitlesResponseDto();
        responseDto.setEmployees(employees);
        responseDto.setTitle(titlesEntity.getTitle());
        responseDto.setFromDate(titlesEntity.getFromDate());
        responseDto.setToDate(titlesEntity.getToDate());
        return responseDto;
    }


    public void deleteTitles(Integer empNo) throws IllegalArgumentException{
        Optional<Titles> titles = titlesRepository.findById(empNo);

        if (!titles.isPresent()) {
            throw new IllegalArgumentException("Titles not found");
        }
        titlesRepository.delete(titles.get());
    }

}
