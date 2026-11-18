package com.company.ems.service.impl;

import com.company.ems.entity.Department;
import com.company.ems.repository.DepartmentRepository;
import com.company.ems.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public  DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long departmentId){
        return  departmentRepository.findById(departmentId).orElseThrow(()-> new RuntimeException("Department Not Found With ID : "+ departmentId));
    }

    @Override
    public List<Department> getAllDepartments() {
        return List.of();
    }

    @Override
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Long departmentId,Department department){
        Department existingDepartment = departmentRepository.findById(departmentId).orElseThrow(()->new RuntimeException("Department not Found With Id:"+departmentId));

        existingDepartment.setDepartmentName(department.getDepartmentName());

        existingDepartment.setLocation(department.getLocation());

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deletDepartment(Long departmentId) {

    }

    @Override
    public void deleteDepartment(long departmentId){
        Department existingDepartment = departmentRepository.findById(departmentId).orElseThrow(()->new RuntimeException("Department not Found With Id:"+departmentId));
        departmentRepository.delete(existingDepartment);
    }
}
