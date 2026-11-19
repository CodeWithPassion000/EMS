package com.company.ems.service;

import com.company.ems.entity.Attendance;
import com.company.ems.dto.AttendanceSummaryDto;
import java.util.List;

public interface AttendanceService {

    Attendance checkIn(Long employeeId);

    Attendance checkOut(Long employeeId);

    Attendance getAttendance(Long attendanceId);

    List<Attendance> getAllAttendance();

    List<Attendance> getAttendanceByEmployee(Long employeeId);

    List<Attendance> getMonthlyAttedance(Long empployeeId,int month,int year);

    AttendanceSummaryDto getAttendanceSummary(Long employeeId);
}