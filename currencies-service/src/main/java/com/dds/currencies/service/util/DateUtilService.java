package com.dds.currencies.service.util;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateUtilService {
    public LocalDate getCurrentDate(){
        return LocalDate.now();
    }
}
