package com.assignment.employeeservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DeptManager {

    @Id
    @Column(name = "dept_no",insertable=false, updatable=false)
    private String deptNo;

    @OneToOne(targetEntity = Departments.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    private Departments departments;

//    @Id
    @Column(name = "emp_no",insertable=false, updatable=false)
    private Integer empNo;

    @OneToOne(targetEntity = Employees.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employees employees;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @Column(name = "to_date")
    private LocalDateTime toDate;
}

