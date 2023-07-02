package com.dds.currencies.utils;

import com.dds.currencies.exception.CurrencyValidationException;

import java.time.LocalDate;

public class DateValidator {

    private static final LocalDate START_DATE = LocalDate.of(2022, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 5, 31);

    public static void validateDates(final LocalDate startDate, final LocalDate endDate) {
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

    public static void validatesYearAndMonth(final Integer year, final Integer month){
        final LocalDate date = LocalDate.of(year, month, 1);
        if (date.isBefore(START_DATE)){
            throw new CurrencyValidationException("Out of range. Date is before " + START_DATE);
        }
        if (date.isAfter(END_DATE)){
            throw new CurrencyValidationException("Out of range. Date is after " + END_DATE);
        }
    }
}
