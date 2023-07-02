package com.dds.currencies.controller;

import com.dds.currencies.exception.CurrencyValidationException;
import com.dds.currencies.service.CurrencyExchangeRatesService;
import com.dds.currencies.api.Currencies;
import com.dds.currencies.api.data.CurrencyRate;
import com.dds.currencies.service.CurrencyExchangeRatesSynchronizationService;
import com.dds.currencies.service.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.dds.currencies.utils.DateValidator.validateDates;
import static com.dds.currencies.utils.DateValidator.validatesYearAndMonth;

@RestController
@RequiredArgsConstructor
public class CurrencyController implements Currencies {

    private final CurrencyService currencyService;
    private final CurrencyExchangeRatesService currencyRateService;
    private final CurrencyExchangeRatesSynchronizationService syncCurrencyRatesService;

    @Override
    public List<CurrencyRate> syncCurrencyRates(final String code, final LocalDate startDate, final LocalDate endDate) {
        validateCurrency(code);
        validateDates(startDate, endDate);
        return syncCurrencyRatesService.syncCurrencyRates(code, startDate, endDate);
    }

    @Override
    public List<CurrencyRate> getCurrencyRates(final String code) {
        validateCurrency(code);
        return currencyRateService.getCurrencyRates(code);
    }

    @Override
    public Double getAverageRate(final String code, final Integer year, final Integer month) {
        validateCurrency(code);
        validatesYearAndMonth(year, month);
        return currencyRateService.getAverageRate(code, year, month);
    }

    public void validateCurrency(final String code) {
        if (Objects.isNull(currencyService.getCurrencyIdByCode(code))) {
            throw new CurrencyValidationException("Unsupported currency code");
        }
    }
}
