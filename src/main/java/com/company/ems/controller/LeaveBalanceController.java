package com.company.ems.controller;

import com.company.ems.entity.LeaveBalance;
import com.company.ems.service.LeaveBalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-balances")
public class LeaveBalanceController {

    private final LeaveBalanceService leaveBalanceService;

    public LeaveBalanceController(
            LeaveBalanceService leaveBalanceService) {

        this.leaveBalanceService = leaveBalanceService;
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<LeaveBalance>
    createLeaveBalance(@PathVariable Long employeeId) {

        return ResponseEntity.ok(
                leaveBalanceService
                        .createLeaveBalance(employeeId));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<LeaveBalance>
    getLeaveBalance(@PathVariable Long employeeId) {

        return ResponseEntity.ok(
                leaveBalanceService
                        .getLeaveBalance(employeeId));
    }

    @GetMapping
    public ResponseEntity<List<LeaveBalance>>
    getAllLeaveBalances() {

        return ResponseEntity.ok(
                leaveBalanceService.getAllLeaveBalances());
    }
}