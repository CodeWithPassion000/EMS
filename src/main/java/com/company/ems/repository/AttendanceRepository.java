package com.company.ems.repository;

import com.company.ems.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByEmployeeEmployeeIdAndAttendanceDate(
            Long employeeId,
            LocalDate attendanceDate);

    List<Attendance> findByEmployeeEmployeeId(Long employeeId);

    @Query("""
        SELECT a
        FROM Attendance a
        WHERE a.employee.employeeId = :employeeId
        AND MONTH(a.attendanceDate)= :month
        AND YEAR(a.attendanceDate)= :year
        """)
    List<Attendance> getMonthlyAttedance(Long employeeId,int month,int year);
}