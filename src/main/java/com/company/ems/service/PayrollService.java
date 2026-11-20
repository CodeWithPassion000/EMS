package com.company.ems.service;

import com.company.ems.entity.Payroll;

import java.util.List;

public interface PayrollService {

    Payroll generatePayroll(Long employeeId, Integer month, Integer year);

    Payroll getPayroll(Long payrollId);

    List<Payroll> getEmployeePayrolls(Long employeeId);

    List<Payroll> getAllPayrolls();
}