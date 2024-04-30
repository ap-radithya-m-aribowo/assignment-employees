package com.assignment.employeeservice.controller;

import com.assignment.employeeservice.dto.DepartmentsRequestDto;
import com.assignment.employeeservice.dto.DepartmentsResponseDto;
import com.assignment.employeeservice.dto.DepartmentsUpdateRequestDto;
import com.assignment.employeeservice.entity.Departments;
import com.assignment.employeeservice.service.DepartmentsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentsController {

    @Autowired
    DepartmentsService departmentsService;

    @PostMapping("/departments")
    @ApiOperation(value = "Create A Department")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Created", response = DepartmentsResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<DepartmentsResponseDto> createDepartments(@Validated @RequestBody DepartmentsRequestDto departmentsRequestDto) {
        return new ResponseEntity<>(departmentsService.createDepartment(departmentsRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/departments")
    @ApiOperation(value = "Get Departments Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = DepartmentsResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<List<Departments>> getAllDepartments(@Validated DepartmentsRequestDto departmentsRequestDto) {
        return new ResponseEntity<>(departmentsService.getAllDepartments(), HttpStatus.OK);
    }

    @PatchMapping("/departments/{deptNo}")
    @ApiOperation(value = "Update a Department Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = DepartmentsResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<DepartmentsResponseDto> updateDepartmentsByDeptNo(@Validated @RequestBody DepartmentsUpdateRequestDto departmentsRequestDto
    ,@PathVariable(value = "deptNo")  String deptNo) throws IllegalArgumentException {
        return new ResponseEntity<>(departmentsService.updateDepartments(deptNo, departmentsRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/departments/{deptNo}")
    @ApiOperation(value = "Delete a Department Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "Not Found"),
            }
    )
    public ResponseEntity<DepartmentsResponseDto> deleteDepartments(@PathVariable(value = "deptNo")  String deptNo) throws IllegalArgumentException {
        departmentsService.deleteDepartments(deptNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
