package com.assignment.employeeservice.repository;

import com.assignment.employeeservice.entity.DeptEmp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, Integer> {
}
