package com.company.ems.controller;

import com.company.ems.entity.LeaveRequest;
import com.company.ems.service.LeaveRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(
            LeaveRequestService leaveRequestService) {

        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    public ResponseEntity<LeaveRequest>
    applyLeave(@RequestBody LeaveRequest leaveRequest) {

        return ResponseEntity.ok(
                leaveRequestService.applyLeave(leaveRequest));
    }

    @PutMapping("/{leaveId}/approve")
    public ResponseEntity<LeaveRequest>
    approveLeave(@PathVariable Long leaveId,
                 @RequestParam String approvedBy) {

        return ResponseEntity.ok(
                leaveRequestService
                        .approveLeave(leaveId, approvedBy));
    }

    @PutMapping("/{leaveId}/reject")
    public ResponseEntity<LeaveRequest>
    rejectLeave(@PathVariable Long leaveId) {

        return ResponseEntity.ok(
                leaveRequestService.rejectLeave(leaveId));
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequest>>
    getAllLeaves() {

        return ResponseEntity.ok(
                leaveRequestService.getAllLeaves());
    }

    @GetMapping("/{leaveId}")
    public ResponseEntity<LeaveRequest>
    getLeaveById(@PathVariable Long leaveId) {

        return ResponseEntity.ok(
                leaveRequestService.getLeaveById(leaveId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequest>>
    getLeavesByEmployee(@PathVariable Long employeeId) {

        return ResponseEntity.ok(
                leaveRequestService
                        .getLeavesByEmployee(employeeId));
    }
}