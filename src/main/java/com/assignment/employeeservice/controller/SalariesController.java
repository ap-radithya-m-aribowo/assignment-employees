package com.assignment.employeeservice.controller;

import com.assignment.employeeservice.dto.*;
import com.assignment.employeeservice.entity.Salaries;
import com.assignment.employeeservice.service.SalariesService;
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
public class SalariesController {

    @Autowired
    SalariesService salariesService;

    @PostMapping("/salaries")
    @ApiOperation(value = "Create A Salaries")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Created", response = SalariesResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<SalariesResponseDto> createSalaries(@Validated @RequestBody SalariesRequestDto requestDto) {
        return new ResponseEntity<>(salariesService.createSalaries(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/salaries")
    @ApiOperation(value = "Get Salaries Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = Salaries.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<List<Salaries>> getAllSalaries(@Validated SalariesRequestDto salariesRequestDto) {
        return new ResponseEntity<>(salariesService.getAllSalaries(), HttpStatus.OK);
    }

    @GetMapping("/salaries/{empNo}")
    @ApiOperation(value = "Get A Salaries Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = Salaries.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<Salaries> getSalariesByDeptNo(@PathVariable(value = "empNo")  Integer empNo) {
        return new ResponseEntity<>(salariesService.getSalariesByEmpNo(empNo), HttpStatus.OK);
    }

    @PatchMapping("/salaries/{empNo}")
    @ApiOperation(value = "Update a Salaries Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = SalariesResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<SalariesResponseDto> updateSalariesByDeptNo(@Validated @RequestBody SalariesUpdateRequestDto salariesUpdateRequestDto
            ,@PathVariable(value = "empNo")  Integer empNo) throws IllegalArgumentException {
        return new ResponseEntity<>(salariesService.updateSalaries(empNo, salariesUpdateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/salaries/{empNo}")
    @ApiOperation(value = "Delete a Salaries Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "Not Found"),
            }
    )
    public ResponseEntity<SalariesResponseDto> deleteSalaries(@PathVariable(value = "empNo")  Integer empNo) throws IllegalArgumentException {
        salariesService.deleteSalaries(empNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
