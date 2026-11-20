package com.company.ems.controller;

import com.company.ems.entity.Holiday;
import com.company.ems.service.impl.HolidayServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

    private final HolidayServiceImpl holidayService;

    public HolidayController(HolidayServiceImpl holidayService){
        this.holidayService = holidayService;
    }

    @PostMapping
    public ResponseEntity<Holiday> createHoliday(@RequestBody Holiday holiday){
        return ResponseEntity.ok(holidayService.createHoliday(holiday));
    }

    @GetMapping
    public ResponseEntity<List<Holiday>> getAllHolidays(){
        return  ResponseEntity.ok(holidayService.getAllHolidays());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Holiday> getHoliday(@PathVariable Long id){
        return ResponseEntity.ok(holidayService.getHoliday(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHoliday(@PathVariable Long id){
        holidayService.deleteHoliday(id);
        return ResponseEntity.ok("Holiday Deleted Successfully");
    }
}
