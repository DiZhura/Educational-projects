package com.example.springexample.service.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "calendar")
public class CalendarProperties {

    private String dateFormat;
    private Integer workDaysOfYear;

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Integer getWorkDaysOfYear() {
        return workDaysOfYear;
    }

    public void setWorkDaysOfYear(Integer workDaysOfYear) {
        this.workDaysOfYear = workDaysOfYear;
    }
}
