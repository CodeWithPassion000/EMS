package com.company.ems.service;

import com.company.ems.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee getEmployeeById(Long employeeId);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long employeeId,Employee employee);

    void deleteEmployee(Long employee);
}
