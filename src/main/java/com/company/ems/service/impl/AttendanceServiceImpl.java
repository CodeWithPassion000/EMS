package com.company.ems.service.impl;

import com.company.ems.dto.AttendanceSummaryDto;
import com.company.ems.entity.Attendance;
import com.company.ems.entity.Employee;
import com.company.ems.enums.AttedanceStatus;
import com.company.ems.repository.AttendanceRepository;
import com.company.ems.repository.EmployeeRepository;
import com.company.ems.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceServiceImpl
        implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceServiceImpl(
            AttendanceRepository attendanceRepository,
            EmployeeRepository employeeRepository) {

        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Attendance checkIn(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee Not Found"));

        attendanceRepository.findByEmployeeEmployeeIdAndAttendanceDate(employeeId,LocalDate.now()).ifPresent(a->{throw new RuntimeException("Employee already checked in today");});

        Attendance attendance =
                Attendance.builder()
                        .employee(employee)
                        .attendanceDate(LocalDate.now())
                        .checkInTime(LocalTime.now())
                        .attendanceStatus(AttedanceStatus.PRESENT)
                        .build();

        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance checkOut(Long employeeId) {

        Attendance attendance =
                attendanceRepository
                        .findByEmployeeEmployeeIdAndAttendanceDate(
                                employeeId,
                                LocalDate.now())
                        .orElseThrow(() ->
                                new RuntimeException("Please check in first"));

        attendance.setCheckOutTime(LocalTime.now());

        long minutes = Duration.between(
                attendance.getCheckInTime(),
                attendance.getCheckOutTime()
        ).toMinutes();

        BigDecimal totalHours =
                BigDecimal.valueOf(minutes)
                        .divide(BigDecimal.valueOf(60), 2,
                                java.math.RoundingMode.HALF_UP);

        attendance.setTotalHours(totalHours);

        if(totalHours.compareTo(BigDecimal.valueOf(8)) >=0){
            attendance.setAttendanceStatus(AttedanceStatus.PRESENT);
        }

        if(totalHours.compareTo(BigDecimal.valueOf(4)) < 0){
            attendance.setAttendanceStatus(AttedanceStatus.HALF_DAY);
        }

        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance getAttendance(Long attendanceId) {

        return attendanceRepository.findById(attendanceId)
                .orElseThrow(() ->
                        new RuntimeException("Attendance Not Found"));
    }

    @Override
    public List<Attendance> getAllAttendance() {

        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> getAttendanceByEmployee(
            Long employeeId) {

        return attendanceRepository
                .findByEmployeeEmployeeId(employeeId);
    }

    @Override
    public List<Attendance> getMonthlyAttedance(Long empployeeId, int month, int year) {
        return attendanceRepository.getMonthlyAttedance(empployeeId,month,year);
    }

    @Override
    public AttendanceSummaryDto getAttendanceSummary(Long employeeId) {

        List<Attendance> records = attendanceRepository.findByEmployeeEmployeeId(employeeId);

        long present =  records.stream().filter(a->a.getAttendanceStatus() == AttedanceStatus.PRESENT).count();

        long half = records.stream().filter(a->a.getAttendanceStatus() == AttedanceStatus.HALF_DAY).count();

        return AttendanceSummaryDto.builder().employeeId(employeeId).totalDays((long) records.size()).presentDays(present).halfDays(half).absentDays(0L).build();
    }

}