package com.company.ems.repository;

import com.company.ems.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository
        extends JpaRepository<Holiday, Long> {
}
