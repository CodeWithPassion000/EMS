package com.company.ems.service.impl;

import com.company.ems.entity.Employee;
import com.company.ems.entity.EmployeeDocument;
import com.company.ems.repository.EmployeeDocumentRepository;
import com.company.ems.repository.EmployeeRepository;
import com.company.ems.service.EmployeeDocumentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDocumentServiceImpl implements EmployeeDocumentService {

    private final EmployeeDocumentRepository documentRepository;

    private final EmployeeRepository employeeRepository;

    public EmployeeDocumentServiceImpl(EmployeeDocumentRepository documentRepository, EmployeeRepository employeeRepository) {

        this.documentRepository = documentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDocument uploadDocument(EmployeeDocument employeeDocument) {

        Employee employee = employeeRepository.findById(employeeDocument.getEmployee().getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee Not Found"));

        employeeDocument.setEmployee(employee);

        return documentRepository.save(employeeDocument);
    }

    @Override
    public List<EmployeeDocument>
    getEmployeeDocuments(Long employeeId) {

        return documentRepository.findByEmployeeEmployeeId(employeeId);
    }

    @Override
    public EmployeeDocument getDocument(Long documentId) {

        return documentRepository.findById(documentId).orElseThrow(() -> new RuntimeException("Document Not Found"));
    }

    @Override
    public void deleteDocument(Long documentId) {

        documentRepository.deleteById(documentId);
    }

    @Override
    public List<EmployeeDocument>
    getAllDocuments() {

        return documentRepository.findAll();
    }
}