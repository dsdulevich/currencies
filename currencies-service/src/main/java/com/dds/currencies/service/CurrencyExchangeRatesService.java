package com.dds.currencies.service;

import com.dds.currencies.api.data.CurrencyRate;
import com.dds.currencies.service.currency.CurrencyRateService;
import com.dds.currencies.validator.CurrencyValidator;
import com.dds.currencies.validator.DateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeRatesService {
    private final CurrencyRateService currencyRateService;

    private final CurrencyValidator currencyValidator;
    private final DateValidator dateValidator;

    public List<CurrencyRate> getCurrencyRates(final String code) {
        currencyValidator.validate(code);

        return currencyRateService.findAllByCode(code);
    }

    public Double getAverageRate(final String code, final Integer year, final Integer month) {
        currencyValidator.validate(code);
        dateValidator.validatesYearAndMonth(year, month);

        final LocalDate startDate = LocalDate.of(year, month, 1);
        final LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        return currencyRateService.getAverageRate(code, startDate, endDate);
    }
}
