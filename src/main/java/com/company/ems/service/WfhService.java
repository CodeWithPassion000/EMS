package com.company.ems.service;

import com.company.ems.entity.WfhRequest;

import java.util.List;

public interface WfhService {

    WfhRequest applyWfh(WfhRequest request);

    WfhRequest approveWfh(Long wfhId, String approvedBy);

    WfhRequest rejectWfh(Long wfhId);

    List<WfhRequest> getAllRequests();

    List<WfhRequest> getEmployeeRequests(Long employeeId);
}
