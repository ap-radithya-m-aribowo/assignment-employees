package com.assignment.employeeservice.repository;

import com.assignment.employeeservice.entity.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalariesRepository extends JpaRepository<Salaries, Integer> {
}
