package com.company.ems.repository;

import com.company.ems.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PayrollRepository
        extends JpaRepository<Payroll, Long> {

    List<Payroll> findByEmployeeEmployeeId(Long employeeId);

    Optional<Payroll> findByEmployeeEmployeeIdAndMonthNoAndYearNo(Long employeeId, Integer monthNo, Integer yearNo);
}