package com.company.ems.service;

import com.company.ems.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment(Department department);

    Department getDepartmentById(Long departmentId);

    List<Department> getAllDepartments();

    Department updateDepartment(Long departmentId,Department department);

    void deletDepartment(Long departmentId);

    List<Department> getAllDepartment();

    void deleteDepartment(long departmentId);
}
