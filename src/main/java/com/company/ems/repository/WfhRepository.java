package com.company.ems.repository;

import com.company.ems.entity.WfhRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WfhRepository
        extends JpaRepository<WfhRequest, Long> {

    List<WfhRequest> findByEmployeeEmployeeId(Long employeeId);
}