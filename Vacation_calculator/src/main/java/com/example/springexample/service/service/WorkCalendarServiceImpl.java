package com.example.springexample.service.service;

import com.example.springexample.service.properties.CalendarProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class WorkCalendarServiceImpl implements WorkCalendarService {

    @Autowired
    protected CalendarProperties calendarProperties;

    private final Set<LocalDate> holidays = new HashSet<>();

    @PostConstruct
    private void init() {
        addHolidays("01.01.2025");
        addHolidays("02.01.2025");
        addHolidays("03.01.2025");
        addHolidays("06.01.2025");
        addHolidays("07.01.2025");
        addHolidays("08.01.2025");
        addHolidays("01.05.2025");
        addHolidays("02.05.2025");
        addHolidays("08.05.2025");
        addHolidays("09.05.2025");
        addHolidays("12.06.2025");
        addHolidays("13.06.2025");
    }

    @Override
    public boolean isWorkDay(LocalDate date) {
        return !isWeekend(date) && !isHoliday(date);
    }

    private boolean isWeekend(LocalDate date) {
        return DayOfWeek.SUNDAY == date.getDayOfWeek() || DayOfWeek.SATURDAY == date.getDayOfWeek();
    }

    private boolean isHoliday(LocalDate date) {
        init();
        return holidays.contains(date);
    }

    private void addHolidays(String dayString) {
        LocalDate localDate = LocalDate.parse(dayString, DateTimeFormatter.ofPattern(calendarProperties.getDateFormat()));
        holidays.add(localDate);
    }

}
