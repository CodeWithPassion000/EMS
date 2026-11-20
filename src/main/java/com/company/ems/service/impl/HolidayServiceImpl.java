package com.company.ems.service.impl;

import com.company.ems.entity.Holiday;
import com.company.ems.repository.HolidayRepository;
import com.company.ems.service.HolidayService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;

    public HolidayServiceImpl(HolidayRepository holidayRepository){
        this.holidayRepository = holidayRepository;
    }

    @Override
    public Holiday createHoliday(Holiday holiday) {
        return holidayRepository.save(holiday);
    }

    @Override
    public Holiday getHoliday(Long holidayId) {
        return holidayRepository.findById(holidayId).orElseThrow(()-> new RuntimeException("Holiday Not Found"));
    }

    @Override
    public List<Holiday> getAllHolidays() {
        return holidayRepository.findAll();
    }

    @Override
    public void deleteHoliday(Long holidayId) {
        holidayRepository.deleteById(holidayId);
    }
}
