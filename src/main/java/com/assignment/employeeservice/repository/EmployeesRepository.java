package com.assignment.employeeservice.repository;

import com.assignment.employeeservice.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
}
