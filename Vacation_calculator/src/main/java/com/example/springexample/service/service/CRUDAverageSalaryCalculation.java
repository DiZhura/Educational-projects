package com.example.springexample.service.service;

import java.time.LocalDate;

public interface CRUDAverageSalaryCalculation<T> {
    T get(Double item, LocalDate date1, LocalDate date2);

}
