package com.assignment.employeeservice.repository;

import com.assignment.employeeservice.entity.Titles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitlesRepository extends JpaRepository<Titles, Integer> {
}
