package com.company.ems.service.impl;

import com.company.ems.entity.Department;
import com.company.ems.entity.Employee;
import com.company.ems.entity.Role;
import com.company.ems.repository.DepartmentRepository;
import com.company.ems.repository.EmployeeRepository;
import com.company.ems.repository.RoleRepository;
import com.company.ems.service.EmployeeService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,DepartmentRepository departmentRepository,RoleRepository roleRepository){
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Employee createEmployee(Employee employee){
        Department department = departmentRepository.findById(employee.getDepartment().getDepartmentId()).orElseThrow(()->new RuntimeException("Department Not Found"));

        Role role = roleRepository.findById(employee.getRole().getRoleId()).orElseThrow(()->new RuntimeException("Role Not Found"));

        Employee newEmployee = Employee.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .salary(employee.getSalary())
                .employeCode(employee.getEmployeCode())
                .password(employee.getPassword())
                .joiningDate(employee.getJoiningDate())
                .status(employee.getStatus())
                .department(department)
                .role(employee.getRole())
                .build();

        return employeeRepository.save(newEmployee);


    }

    @Override
    public Employee getEmployeeById(Long employeeId){
       return employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee Not Found"));

    }

    @Override
    public List<Employee> getAllEmployees() {
        System.out.println("Comming");
       return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee employee) {
        Employee employee1 = employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee Not Found"));

        Department department = departmentRepository.findById(employee.getDepartment().getDepartmentId()).orElseThrow(()->new RuntimeException("Department Not Found"));

        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setPhoneNumber(employee.getPhoneNumber());
        employee1.setEmail(employee.getEmail());
        employee1.setSalary(employee.getSalary());
        employee1.setStatus(employee.getStatus());
        employee1.setDepartment(department);

        return employeeRepository.save(employee1);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new RuntimeException("Employee Not Found"));

        employeeRepository.delete(employee);

    }
}
