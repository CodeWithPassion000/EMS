package com.company.ems.service;

import com.company.ems.entity.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {

    LeaveRequest applyLeave(LeaveRequest leaveRequest);

    LeaveRequest approveLeave(Long leaveId, String approvedBy);

    LeaveRequest rejectLeave(Long leaveId);

    LeaveRequest getLeaveById(Long leaveId);

    List<LeaveRequest> getAllLeaves();

    List<LeaveRequest> getLeavesByEmployee(Long employeeId);
}