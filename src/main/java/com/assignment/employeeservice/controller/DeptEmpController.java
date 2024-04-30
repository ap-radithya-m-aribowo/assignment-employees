package com.assignment.employeeservice.controller;

import com.assignment.employeeservice.dto.DeptEmpRequestDto;
import com.assignment.employeeservice.dto.DeptEmpResponseDto;
import com.assignment.employeeservice.dto.DeptEmpUpdateRequestDto;
import com.assignment.employeeservice.entity.DeptEmp;
import com.assignment.employeeservice.service.DeptEmpService;
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
public class DeptEmpController {
    
    @Autowired
    private DeptEmpService deptEmpService;

    @PostMapping("/dept-emp")
    @ApiOperation(value = "Create A DeptEmp")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Created", response = DeptEmpResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<DeptEmpResponseDto> createDeptEmp(@Validated @RequestBody DeptEmpRequestDto requestDto) {
        return new ResponseEntity<>(deptEmpService.createDeptEmp(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/dept-emp")
    @ApiOperation(value = "Get DeptEmp Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = DeptEmp.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<List<DeptEmp>> getAllDeptEmp(@Validated DeptEmpRequestDto deptEmpRequestDto) {
        return new ResponseEntity<>(deptEmpService.getAllDeptEmp(), HttpStatus.OK);
    }

    @GetMapping("/dept-emp/{empNo}")
    @ApiOperation(value = "Get A DeptEmp Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = DeptEmp.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<DeptEmp> getDeptEmpByDeptNo(@PathVariable(value = "empNo")  Integer empNo) {
        return new ResponseEntity<>(deptEmpService.getDeptEmpByEmpNo(empNo), HttpStatus.OK);
    }

    @PatchMapping("/dept-emp/{empNo}")
    @ApiOperation(value = "Update a DeptEmp Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK", response = DeptEmpResponseDto.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = ResponseEntity.class),
            }
    )
    public ResponseEntity<DeptEmpResponseDto> updateDeptEmpByEmpNo(@Validated @RequestBody DeptEmpUpdateRequestDto deptEmpUpdateRequestDto
            ,@PathVariable(value = "empNo")  Integer empNo) throws IllegalArgumentException {
        return new ResponseEntity<>(deptEmpService.updateDeptEmp(empNo, deptEmpUpdateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/dept-emp/{empNo}")
    @ApiOperation(value = "Delete a DeptEmp Data")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "Not Found"),
            }
    )
    public ResponseEntity<DeptEmpResponseDto> deleteEmpEmp(@PathVariable(value = "empNo")  Integer empNo) throws IllegalArgumentException {
        deptEmpService.deleteDeptEmp(empNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
