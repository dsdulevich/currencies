package com.dds.currencies.controller;

import com.dds.currencies.service.CurrencyExchangeRatesService;
import com.dds.currencies.api.Currencies;
import com.dds.currencies.api.data.CurrencyRate;
import com.dds.currencies.service.CurrencyExchangeRatesSynchronizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CurrencyController implements Currencies {

    private final CurrencyExchangeRatesService currencyRateService;
    private final CurrencyExchangeRatesSynchronizationService syncCurrencyRatesService;

    @Override
    public List<CurrencyRate> syncCurrencyRates(final String code, final LocalDate startDate, final LocalDate endDate) {
        log.debug("Request for sync currency rates: code - {}, start - {}, end - {}", code, startDate, endDate);
        return syncCurrencyRatesService.syncCurrencyRates(code, startDate, endDate);
    }

    @Override
    public List<CurrencyRate> getCurrencyRates(final String code) {
        log.debug("Request for currency rates by code: {}", code);
        return currencyRateService.getCurrencyRates(code);
    }

    @Override
    public Double getAverageRate(final String code, final Integer year, final Integer month) {
        log.debug("Request for currency average rate by code: {}, year: {}, month: {}", code, year, month);
        return currencyRateService.getAverageRate(code, year, month);
    }
}
