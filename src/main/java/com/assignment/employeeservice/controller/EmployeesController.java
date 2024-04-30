package com.assignment.employeeservice.controller;

import com.assignment.employeeservice.dto.*;
import com.assignment.employeeservice.entity.Departments;
import com.assignment.employeeservice.entity.Employees;
import com.assignment.employeeservice.service.EmployeesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeesController {

    @Autowired
    EmployeesService employeesService;

    @PostMapping("/employees")
    @ApiOperation(value = "Create An Employee")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Created", response = DepartmentsResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<EmployeesResponseDto> createEmployees(@Validated @RequestBody EmployeesRequestDto employeesRequestDto) {
        return new ResponseEntity<>(employeesService.createEmployees(employeesRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    @ApiOperation(value = "Get Departments Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = DepartmentsResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<List<Employees>> getAllDepartments(@Validated EmployeesRequestDto employeesRequestDto) {
        return new ResponseEntity<>(employeesService.getAllDepartments(), HttpStatus.OK);
    }

    @PatchMapping("/employees/{empNo}")
    @ApiOperation(value = "Update an Employees Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = DepartmentsResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<EmployeesResponseDto> updateEmployeesById(@Validated @RequestBody EmployeesUpdateRequestDto employeesUpdateRequestDto
            ,@PathVariable(value = "empNo")  Integer empNo) throws IllegalArgumentException {
        return new ResponseEntity<>(employeesService.updateEmployees(empNo, employeesUpdateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{empNo}")
    @ApiOperation(value = "Delete an Employees Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "Not Found"),
            }
    )
    public ResponseEntity<DepartmentsResponseDto> deleteDepartments(@PathVariable(value = "empNo")  Integer empNo) throws IllegalArgumentException {
        employeesService.deleteEmployees(empNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
