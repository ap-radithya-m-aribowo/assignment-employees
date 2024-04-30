package com.assignment.employeeservice.controller;

import com.assignment.employeeservice.dto.*;
import com.assignment.employeeservice.entity.Titles;
import com.assignment.employeeservice.service.TitlesService;
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
public class TitlesController {

    @Autowired
    TitlesService titlesService;

    @PostMapping("/titles")
    @ApiOperation(value = "Create A Titles")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Created", response = TitlesResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<TitlesResponseDto> createTitles(@Validated @RequestBody TitlesRequestDto titlesRequestDto) {
        return new ResponseEntity<>(titlesService.createTitles(titlesRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/titles")
    @ApiOperation(value = "Get Titles Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = TitlesResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<List<Titles>> getAllTitles(@Validated TitlesRequestDto titlesRequestDto) {
        return new ResponseEntity<>(titlesService.getAllTitles(), HttpStatus.OK);
    }

    @GetMapping("/titles/{empNo}")
    @ApiOperation(value = "Get A Titles Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = TitlesResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<Titles> getTitlesByDeptNo(@PathVariable(value = "empNo")  Integer empNo) {
        return new ResponseEntity<>(titlesService.getTitlesByEmpNo(empNo), HttpStatus.OK);
    }

    @PatchMapping("/titles/{empNo}")
    @ApiOperation(value = "Update a Title Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = TitlesResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<TitlesResponseDto> updateTitlesByDeptNo(@Validated @RequestBody TitlesUpdateRequestDto titlesUpdateRequestDto
            ,@PathVariable(value = "empNo")  Integer empNo) throws IllegalArgumentException {
        return new ResponseEntity<>(titlesService.updateTitles(empNo, titlesUpdateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/titles/{empNo}")
    @ApiOperation(value = "Delete a Title Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "Not Found"),
            }
    )
    public ResponseEntity<TitlesResponseDto> deleteTitles(@PathVariable(value = "empNo")  Integer empNo) throws IllegalArgumentException {
        titlesService.deleteTitles(empNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
