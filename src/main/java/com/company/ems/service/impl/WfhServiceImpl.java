package com.company.ems.service.impl;

import com.company.ems.entity.Employee;
import com.company.ems.entity.WfhRequest;
import com.company.ems.enums.WfhStatus;
import com.company.ems.repository.EmployeeRepository;
import com.company.ems.repository.WfhRepository;
import com.company.ems.service.WfhService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WfhServiceImpl
        implements WfhService {

    private final WfhRepository wfhRepository;
    private final EmployeeRepository employeeRepository;

    public WfhServiceImpl(WfhRepository wfhRepository, EmployeeRepository employeeRepository) {

        this.wfhRepository = wfhRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public WfhRequest applyWfh(WfhRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployee().getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee Not Found"));

        request.setEmployee(employee);

        return wfhRepository.save(request);
    }

    @Override
    public WfhRequest approveWfh(Long wfhId, String approvedBy) {

        WfhRequest request = wfhRepository.findById(wfhId).orElseThrow(() -> new RuntimeException("WFH Request Not Found"));

        request.setStatus(WfhStatus.APPROVED);

        request.setApprovedBy(approvedBy);

        request.setApprovedDate(LocalDateTime.now());

        return wfhRepository.save(request);
    }

    @Override
    public WfhRequest rejectWfh(
            Long wfhId) {

        WfhRequest request = wfhRepository.findById(wfhId).orElseThrow(() -> new RuntimeException("WFH Request Not Found"));

        request.setStatus(WfhStatus.REJECTED);

        return wfhRepository.save(request);
    }

    @Override
    public List<WfhRequest> getAllRequests() {

        return wfhRepository.findAll();
    }

    @Override
    public List<WfhRequest> getEmployeeRequests(Long employeeId) {

        return wfhRepository.findByEmployeeEmployeeId(employeeId);
    }
}