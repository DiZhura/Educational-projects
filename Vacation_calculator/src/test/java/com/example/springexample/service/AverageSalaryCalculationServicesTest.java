package com.example.springexample.service;

import com.example.springexample.service.service.AverageSalaryCalculationServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AverageSalaryCalculationServicesTest {

    @Autowired
    AverageSalaryCalculationServices averageSalaryCalculationServices;

    @Test
    @DisplayName("Test Get")
    public void testGet() {
        double paymentTest = 1000000.00;
        LocalDate firstDateTest = LocalDate.parse("01.01.2025", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate secondDateTest = LocalDate.parse("30.01.2025", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        assertEquals(averageSalaryCalculationServices.get(paymentTest, firstDateTest, secondDateTest), 64777.33);
    }
}
