package com.company.ems.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceSummaryDto {

    private Long employeeId;

    private Long totalDays;

    private Long presentDays;

    private Long halfDays;

    private Long absentDays;
}