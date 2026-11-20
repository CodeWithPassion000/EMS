package com.company.ems.controller;

import com.company.ems.entity.Payroll;
import com.company.ems.service.PayrollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {

        this.payrollService = payrollService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Payroll>
    generatePayroll(@RequestParam Long employeeId, @RequestParam Integer month, @RequestParam Integer year) {

        return ResponseEntity.ok(payrollService.generatePayroll(employeeId, month, year));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payroll> getPayroll(@PathVariable Long id) {

        return ResponseEntity.ok(payrollService.getPayroll(id));
    }

    @GetMapping
    public ResponseEntity<List<Payroll>>
    getAllPayrolls() {

        return ResponseEntity.ok( payrollService.getAllPayrolls());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Payroll>>
    getEmployeePayrolls(@PathVariable Long employeeId) {

        return ResponseEntity.ok(payrollService.getEmployeePayrolls(employeeId));
    }
}