package com.company.ems.service.impl;

import com.company.ems.entity.Employee;
import com.company.ems.entity.LeaveBalance;
import com.company.ems.repository.EmployeeRepository;
import com.company.ems.repository.LeaveBalanceRepository;
import com.company.ems.service.LeaveBalanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveBalanceServiceImpl
        implements LeaveBalanceService {

    private final LeaveBalanceRepository leaveBalanceRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveBalanceServiceImpl(
            LeaveBalanceRepository leaveBalanceRepository,
            EmployeeRepository employeeRepository) {

        this.leaveBalanceRepository = leaveBalanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public LeaveBalance createLeaveBalance(Long employeeId) {

        Employee employee =
                employeeRepository.findById(employeeId)
                        .orElseThrow(() ->
                                new RuntimeException("Employee Not Found"));

        LeaveBalance leaveBalance =
                LeaveBalance.builder()
                        .employee(employee)
                        .casualLeave(12)
                        .sickLeave(8)
                        .earnedLeave(18)
                        .build();

        return leaveBalanceRepository.save(leaveBalance);
    }

    @Override
    public LeaveBalance getLeaveBalance(Long employeeId) {

        return leaveBalanceRepository
                .findByEmployeeEmployeeId(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Leave Balance Not Found"));
    }

    @Override
    public List<LeaveBalance> getAllLeaveBalances() {
        return leaveBalanceRepository.findAll();
    }
}