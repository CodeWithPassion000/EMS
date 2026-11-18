package com.company.ems.service.impl;

import com.company.ems.entity.Employee;
import com.company.ems.entity.LeaveRequest;
import com.company.ems.repository.EmployeeRepository;
import com.company.ems.repository.LeaveRequestRepository;
import com.company.ems.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveRequestServiceImpl
        implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestServiceImpl(
            LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository) {

        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public LeaveRequest applyLeave(
            LeaveRequest leaveRequest) {

        Employee employee =
                employeeRepository.findById(
                                leaveRequest.getEmployee().getEmployeeId())
                        .orElseThrow(() ->
                                new RuntimeException("Employee Not Found"));

        long days =
                ChronoUnit.DAYS.between(
                        leaveRequest.getStartDate(),
                        leaveRequest.getEndDate()) + 1;

        leaveRequest.setEmployee(employee);
        leaveRequest.setTotalDays((int) days);

        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest approveLeave(
            Long leaveId,
            String approvedBy) {

        LeaveRequest leave =
                leaveRequestRepository.findById(leaveId)
                        .orElseThrow(() ->
                                new RuntimeException("Leave Not Found"));

        leave.setStatus(
                com.company.ems.enums.LeaveStatus.APPROVED);

        leave.setApprovedBy(approvedBy);
        leave.setApprovedDate(java.time.LocalDateTime.now());

        return leaveRequestRepository.save(leave);
    }

    @Override
    public LeaveRequest rejectLeave(Long leaveId) {

        LeaveRequest leave =
                leaveRequestRepository.findById(leaveId)
                        .orElseThrow(() ->
                                new RuntimeException("Leave Not Found"));

        leave.setStatus(
                com.company.ems.enums.LeaveStatus.REJECTED);

        return leaveRequestRepository.save(leave);
    }

    @Override
    public LeaveRequest getLeaveById(Long leaveId) {

        return leaveRequestRepository.findById(leaveId)
                .orElseThrow(() ->
                        new RuntimeException("Leave Not Found"));
    }

    @Override
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public List<LeaveRequest> getLeavesByEmployee(
            Long employeeId) {

        return leaveRequestRepository
                .findByEmployeeEmployeeId(employeeId);
    }
}