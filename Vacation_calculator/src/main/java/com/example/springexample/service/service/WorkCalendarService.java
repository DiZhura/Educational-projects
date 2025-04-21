package com.example.springexample.service.service;

import java.time.LocalDate;

public interface WorkCalendarService {
    boolean isWorkDay(LocalDate date);
}
