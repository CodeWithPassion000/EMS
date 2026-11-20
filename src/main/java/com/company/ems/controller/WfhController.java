package com.company.ems.controller;

import com.company.ems.entity.WfhRequest;
import com.company.ems.service.WfhService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wfh")
public class WfhController {

    private final WfhService wfhService;

    public WfhController(WfhService wfhService) {

        this.wfhService = wfhService;
    }

    @PostMapping
    public ResponseEntity<WfhRequest> applyWfh(@RequestBody WfhRequest request) {

        return ResponseEntity.ok(wfhService.applyWfh(request));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<WfhRequest> approveWfh(@PathVariable Long id, @RequestParam String approvedBy) {

        return ResponseEntity.ok(wfhService.approveWfh(id, approvedBy));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<WfhRequest> rejectWfh(@PathVariable Long id) {

        return ResponseEntity.ok(wfhService.rejectWfh(id));
    }

    @GetMapping
    public ResponseEntity<List<WfhRequest>> getAllRequests() {

        return ResponseEntity.ok(wfhService.getAllRequests());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<WfhRequest>> getEmployeeRequests(@PathVariable Long employeeId) {

        return ResponseEntity.ok(wfhService.getEmployeeRequests(employeeId));
    }
}