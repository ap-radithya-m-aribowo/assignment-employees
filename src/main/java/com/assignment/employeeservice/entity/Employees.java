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
@Table(name = "employees")
public class Employees {

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(targetEntity = Salaries.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Salaries salary;

    @OneToOne(targetEntity = Titles.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Titles title;

    @OneToOne(targetEntity = DeptEmp.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private DeptEmp deptEmp;

    @OneToOne(targetEntity = DeptManager.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private DeptManager deptManager;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    public enum Gender {
        M,
        F
    }

    @Column(name = "hire_date")
    private LocalDateTime hireDate;

}
