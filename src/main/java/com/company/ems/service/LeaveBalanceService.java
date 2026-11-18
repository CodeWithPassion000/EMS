package com.company.ems.service;

import com.company.ems.entity.LeaveBalance;

import java.util.List;

public interface LeaveBalanceService {

    LeaveBalance createLeaveBalance(Long employeeId);

    LeaveBalance getLeaveBalance(Long employeeId);

    List<LeaveBalance> getAllLeaveBalances();

}