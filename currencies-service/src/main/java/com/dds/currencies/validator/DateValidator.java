package com.dds.currencies.validator;

import com.dds.currencies.exception.CurrencyValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateValidator {

    @Value("${dds.currencies.start-date}")
    private LocalDate START_DATE;

    @Value("${dds.currencies.end-date}")
    private LocalDate END_DATE;

    public void validateDates(final LocalDate startDate, final LocalDate endDate) {
        if (startDate.isAfter(endDate)){
            throw new CurrencyValidationException("Invalid range. Start date is larger than end date");
        }
        if (startDate.isBefore(START_DATE)) {
            throw new CurrencyValidationException("Out of range. Start date is before " + START_DATE);
        }
        if (endDate.isAfter(END_DATE)) {
            throw new CurrencyValidationException("Out of range. End date is after " + END_DATE);
        }
    }

    public void validatesYearAndMonth(final Integer year, final Integer month){
        final LocalDate date = LocalDate.of(year, month, 1);
        if (date.isBefore(START_DATE)){
            throw new CurrencyValidationException("Out of range. Date is before " + START_DATE);
        }
        if (date.isAfter(END_DATE)){
            throw new CurrencyValidationException("Out of range. Date is after " + END_DATE);
        }
    }
}
