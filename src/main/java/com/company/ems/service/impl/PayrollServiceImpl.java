package com.company.ems.service.impl;

import com.company.ems.entity.Attendance;
import com.company.ems.entity.Employee;
import com.company.ems.entity.Payroll;
import com.company.ems.enums.AttedanceStatus;
import com.company.ems.repository.AttendanceRepository;
import com.company.ems.repository.EmployeeRepository;
import com.company.ems.repository.PayrollRepository;
import com.company.ems.service.PayrollService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollServiceImpl
        implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public PayrollServiceImpl(PayrollRepository payrollRepository, EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {

        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Payroll generatePayroll(
            Long employeeId,
            Integer month,
            Integer year) {

        payrollRepository.findByEmployeeEmployeeIdAndMonthNoAndYearNo(employeeId, month, year).ifPresent(p -> {throw new RuntimeException("Payroll already generated");});

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee Not Found"));

        List<Attendance> attendances = attendanceRepository.findByEmployeeEmployeeId(employeeId);

        long presentDays = attendances.stream().filter(a -> a.getAttendanceStatus() == AttedanceStatus.PRESENT)
                        .count();

        long halfDays = attendances.stream().filter(a -> a.getAttendanceStatus() == AttedanceStatus.HALF_DAY).count();

        double paidDays = presentDays + (halfDays * 0.5);

        BigDecimal monthlySalary = BigDecimal.valueOf(employee.getSalary());

        BigDecimal perDaySalary = monthlySalary.divide(BigDecimal.valueOf(30), 2, RoundingMode.HALF_UP);

        BigDecimal netSalary = perDaySalary.multiply(BigDecimal.valueOf(paidDays));

        BigDecimal deduction = monthlySalary.subtract(netSalary);

        Payroll payroll = Payroll.builder()
                        .employee(employee)
                        .monthNo(month)
                        .yearNo(year)
                        .grossSalary(monthlySalary)
                        .deduction(deduction)
                        .netSalary(netSalary)
                        .build();

        return payrollRepository.save(payroll);
    }

    @Override
    public Payroll getPayroll(Long payrollId) {

        return payrollRepository.findById(payrollId).orElseThrow(() -> new RuntimeException("Payroll Not Found"));
    }

    @Override
    public List<Payroll> getEmployeePayrolls(Long employeeId) {

        return payrollRepository.findByEmployeeEmployeeId(employeeId);
    }

    @Override
    public List<Payroll> getAllPayrolls() {

        return payrollRepository.findAll();
    }
}