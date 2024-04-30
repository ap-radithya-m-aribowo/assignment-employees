package com.assignment.employeeservice.repository;

import com.assignment.employeeservice.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepository extends JpaRepository<Departments, String> {
}
