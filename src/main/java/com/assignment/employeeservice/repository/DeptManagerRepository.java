package com.assignment.employeeservice.repository;

import com.assignment.employeeservice.entity.DeptManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptManagerRepository extends JpaRepository<DeptManager, Integer> {
}
