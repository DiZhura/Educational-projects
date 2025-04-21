package com.example.springexample.service.controller;

import com.example.springexample.service.properties.CalendarProperties;
import com.example.springexample.service.service.AverageSalaryCalculationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@RestController
@RequestMapping("/api/calculate")
public class AverageSalaryCalculationController {
    @Autowired
    protected AverageSalaryCalculationServices averageSalaryCalculationServices;
    @Autowired
    protected CalendarProperties calendarProperties;

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(name = "payment") Double payment,
                                 @RequestParam(name = "firstDate") String firstDate,
                                 @RequestParam(name = "secondDate") String secondDate) {

        try {
            String dateFormat = calendarProperties.getDateFormat();
            LocalDate firstParsedDate = LocalDate.parse(firstDate, DateTimeFormatter.ofPattern(dateFormat));
            LocalDate secondParsedDate = LocalDate.parse(secondDate, DateTimeFormatter.ofPattern(dateFormat));
            return new ResponseEntity<>(averageSalaryCalculationServices.get(payment, firstParsedDate, secondParsedDate), HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>("Переданное значение не соответствует шаблону", HttpStatus.BAD_REQUEST);
        }
    }
}
