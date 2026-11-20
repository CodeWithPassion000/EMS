package com.company.ems.controller;

import com.company.ems.entity.EmployeeDocument;
import com.company.ems.service.EmployeeDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class EmployeeDocumentController {

    private final EmployeeDocumentService documentService;

    public EmployeeDocumentController(EmployeeDocumentService documentService) {

        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDocument> uploadDocument(@RequestBody EmployeeDocument employeeDocument) {

        return ResponseEntity.ok(documentService.uploadDocument(employeeDocument));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDocument>> getAllDocuments() {

        return ResponseEntity.ok(documentService.getAllDocuments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDocument> getDocument(@PathVariable Long id) {

        return ResponseEntity.ok(documentService.getDocument(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeDocument>> getEmployeeDocuments(@PathVariable Long employeeId) {

        return ResponseEntity.ok(documentService.getEmployeeDocuments(employeeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {

        documentService.deleteDocument(id);

        return ResponseEntity.ok("Document Deleted Successfully");
    }
}