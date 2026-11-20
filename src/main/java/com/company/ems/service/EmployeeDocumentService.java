package com.company.ems.service;

import com.company.ems.entity.EmployeeDocument;

import java.util.List;

public interface EmployeeDocumentService {

    EmployeeDocument uploadDocument(EmployeeDocument employeeDocument);

    List<EmployeeDocument> getEmployeeDocuments(Long employeeId);

    EmployeeDocument getDocument(Long documentId);

    void deleteDocument(Long documentId);

    List<EmployeeDocument> getAllDocuments();
}