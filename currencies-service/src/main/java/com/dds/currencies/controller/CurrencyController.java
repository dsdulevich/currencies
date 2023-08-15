package com.dds.currencies.controller;

import com.dds.currencies.service.CurrencyExchangeRatesService;
import com.dds.currencies.api.Currencies;
import com.dds.currencies.api.data.CurrencyRate;
import com.dds.currencies.service.CurrencyExchangeRatesSynchronizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController implements Currencies {

    private final CurrencyExchangeRatesService currencyRateService;
    private final CurrencyExchangeRatesSynchronizationService syncCurrencyRatesService;

    @Override
    public List<CurrencyRate> syncCurrencyRates(final String code, final LocalDate startDate, final LocalDate endDate) {
        return syncCurrencyRatesService.syncCurrencyRates(code, startDate, endDate);
    }

    @Override
    public List<CurrencyRate> getCurrencyRates(final String code) {
        return currencyRateService.getCurrencyRates(code);
    }

    @Override
    public Double getAverageRate(final String code, final Integer year, final Integer month) {
        return currencyRateService.getAverageRate(code, year, month);
    }
}
