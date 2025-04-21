package com.example.springexample.service.service;

import com.example.springexample.service.properties.CalendarProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class AverageSalaryCalculationServices implements CRUDAverageSalaryCalculation<Double> {

    @Autowired
    protected WorkCalendarService workCalendarService;
    @Autowired
    protected CalendarProperties calendarProperties;

    @Override
    public Double get(Double payment, LocalDate firstDate, LocalDate secondDate) {
        int daysOfVacation = calculateDate(firstDate, secondDate);
        double vacationsPay = (payment / calendarProperties.getWorkDaysOfYear()) * daysOfVacation;
        return Math.round(vacationsPay * 100.0) / 100.0;
    }

    public int calculateDate(LocalDate firstDate, LocalDate secondDate) {
        LocalDate firstDateFromParams = firstDate.isBefore(secondDate) ? firstDate : secondDate;
        LocalDate secondDateFromParams = firstDate.isBefore(secondDate) ? secondDate : firstDate;
        int count = 1;
        for (LocalDate d = firstDateFromParams; d.isBefore(secondDateFromParams); d = d.plusDays(1)) {
            if (workCalendarService.isWorkDay(d)) {
                count++;
            }
        }
        return count;
    }
}


