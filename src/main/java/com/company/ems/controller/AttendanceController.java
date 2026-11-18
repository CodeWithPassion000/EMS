package com.company.ems.controller;

import com.company.ems.entity.Attendance;
import com.company.ems.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

   public AttendanceController(AttendanceService attendanceService) {

        this.attendanceService= attendanceService;
    }
    @PostMapping("/check-in/{employeeId}")
    public ResponseEntity<Attendance> checkIn(@PathVariable Long employeeId) {

        return  ResponseEntity.ok(attendanceService.checkIn(employeeId));
    }

    @PostMapping("/check-out/{employeeId}")
    public ResponseEntity<Attendance> checkOut(@PathVariable Long employeeId){

    return ResponseEntity.ok(attendanceService.checkOut(employeeId));
    }

   @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendance() {

        return ResponseEntity.ok(attendanceService.getAllAttendance());
   }

    @GetMapping("/{attendanceId}")
    public ResponseEntity<Attendance> getAttendance(@PathVariable  Long attendanceId) {

        return  ResponseEntity.ok(attendanceService.getAttendance(attendanceId));
    }

    @GetMapping ("/employee/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployee(@PathVariable Long employeeId) {

        return ResponseEntity.ok(attendanceService.getAttendanceByEmployee(employeeId));
    }
}