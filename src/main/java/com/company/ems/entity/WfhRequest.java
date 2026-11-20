package com.company.ems.entity;

import com.company.ems.enums.WfhStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "wfh_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WfhRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wfh_id")
    private Long wfhId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "request_date")
    private LocalDate requestDate;

    private String reason;

    @Enumerated(EnumType.STRING)
    private WfhStatus status;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_date")
    private LocalDateTime approvedDate;

    @PrePersist
    public void prePersist() {
        status = WfhStatus.PENDING;
    }
}