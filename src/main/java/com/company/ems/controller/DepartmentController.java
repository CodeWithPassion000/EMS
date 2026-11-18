package com.company.ems.controller;


import com.company.ems.entity.Department;
import com.company.ems.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    // create Department

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
        Department saveDepartment = departmentService.createDepartment(department);

        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }
    // Get Department By ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id){
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    // Get All Departments
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartment(){

        List<Department> departments = departmentService.getAllDepartment();
        return ResponseEntity.ok(departments);
    }

    // Update Department
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department){
        Department updateDepartment = departmentService.updateDepartment(id, department);

        return ResponseEntity.ok(updateDepartment);
    }

    //Delete Department
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department Deleted Successfully");
    }


}
