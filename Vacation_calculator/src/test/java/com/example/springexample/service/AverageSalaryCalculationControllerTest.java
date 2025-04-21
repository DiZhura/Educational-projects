package com.example.springexample.service;

import com.example.springexample.service.controller.AverageSalaryCalculationController;
import com.example.springexample.service.service.AverageSalaryCalculationServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AverageSalaryCalculationControllerTest {

    @Autowired
    AverageSalaryCalculationController averageSalaryCalculationController;
    @Mock
    AverageSalaryCalculationServices averageSalaryCalculationServices1;

    @Test
    @DisplayName("testGet")
    public void testController() {
        Double payment = 1000000.00;
        String firstDate = "01.01.2025";
        String secondDate = "30.01.2025";
        Double expectedSalary = 64777.33;
        ResponseEntity<?> actualResponseEntity = averageSalaryCalculationController.get(payment, firstDate, secondDate);
        ResponseEntity<Double> expected = new ResponseEntity<>(expectedSalary, HttpStatus.OK);
        assertEquals(actualResponseEntity, expected);
    }

    @Test
    @DisplayName("test Wrong Date Format")
    public void testWrongDateFormat() {
        Double payment = 1000000.00;
        String firstDate = "01-01-2025";
        String secondDate = "30.01.2025";
        ResponseEntity<?> actualResponseEntity = averageSalaryCalculationController.get(payment, firstDate, secondDate);
        ResponseEntity<String> expected = new ResponseEntity<>("Переданное значение не соответствует шаблону", HttpStatus.BAD_REQUEST);
        assertEquals(actualResponseEntity, expected);
    }
}
