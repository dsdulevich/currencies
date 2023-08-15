package com.dds.currencies.service;

import com.dds.currencies.api.data.CurrencyRate;
import com.dds.currencies.feign.nb.NationalBankService;
import com.dds.currencies.service.currency.CurrencyRateService;
import com.dds.currencies.validator.CurrencyValidator;
import com.dds.currencies.validator.DateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeRatesSynchronizationService {
    private final NationalBankService nationalBankService;
    private final CurrencyRateService currencyRateService;

    private final CurrencyValidator currencyValidator;
    private final DateValidator dateValidator;

    public List<CurrencyRate> syncCurrencyRates(final String code, final LocalDate startDate, final LocalDate endDate) {
        currencyValidator.validate(code);
        dateValidator.validateDates(startDate, endDate);

        if (isThisRangeInDatabase(code, startDate, endDate)) {
            return currencyRateService.findAllByCodeAndDateBetween(code, startDate, endDate);
        } else {
            return sync(code, startDate, endDate);
        }
    }

    private boolean isThisRangeInDatabase(final String currencyId, final LocalDate startDate, final LocalDate endDate) {
        return currencyRateService.countByCodeAndDateBetween(currencyId, startDate, endDate) ==
                ((endDate.toEpochDay() - startDate.toEpochDay()) + 1);
    }

    private List<CurrencyRate> sync(final String code, final LocalDate startDate, final LocalDate endDate) {
        final Set<LocalDate> ratesFromDatabase = currencyRateService.findAllByCodeAndDateBetween(code, startDate, endDate)
                .stream()
                .map(CurrencyRate::getDate)
                .collect(Collectors.toSet());

        final List<CurrencyRate> newRates = nationalBankService.getCurrencyRates(code, startDate.atStartOfDay(), endDate.atStartOfDay())
                .stream()
                .filter(rate -> ratesFromDatabase.contains(rate.getDate()))
                .map(rate -> new CurrencyRate()
                        .setCode(code)
                        .setRate(rate.getRate())
                        .setDate(rate.getDate()))
                .toList();

        currencyRateService.saveAll(newRates);
        return newRates;
    }
}
