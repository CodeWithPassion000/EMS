package com.company.ems.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payroll")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id")
    private Long payrollId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "month_no")
    private Integer monthNo;

    @Column(name = "year_no")
    private Integer yearNo;

    @Column(name = "gross_salary")
    private BigDecimal grossSalary;

    private BigDecimal deduction;

    @Column(name = "net_salary")
    private BigDecimal netSalary;

    @Column(name = "generated_date")
    private LocalDateTime generatedDate;

    @PrePersist
    public void prePersist() {
        generatedDate = LocalDateTime.now();
    }
}