package com.company.ems.service;

import com.company.ems.entity.Holiday;

import java.util.List;

public interface HolidayService {

    Holiday createHoliday(Holiday holiday);

    Holiday getHoliday(Long holidayId);

    List<Holiday> getAllHolidays();

    void deleteHoliday(Long holidayId);
}
