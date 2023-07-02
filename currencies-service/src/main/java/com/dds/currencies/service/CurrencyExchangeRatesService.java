package com.dds.currencies.service;

import com.dds.currencies.entity.CurrencyRateEntity;
import com.dds.currencies.exception.CurrencyNotFoundException;
import com.dds.currencies.mapper.CurrencyRateMapper;
import com.dds.currencies.repository.CurrencyRateRepository;
import com.dds.currencies.api.data.CurrencyRate;
import com.dds.currencies.service.weekend.WeekendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeRatesService {
    private final WeekendsService weekendsService;
    private final CurrencyRateRepository repository;
    private final CurrencyRateMapper currencyRateMapper;

    public List<CurrencyRate> getCurrencyRates(final String code) {
        return currencyRateMapper.map(repository.findAllByCode(code));
    }

    public Double getAverageRate(final String code, final Integer year, final Integer month) {
        final List<CurrencyRateEntity> workingRates = getWorkingRates(code, year, month);
        if (workingRates.isEmpty()) {
            throw new CurrencyNotFoundException("There are no exchange rates for this month");
        }
        final Double product = workingRates
                .stream()
                .map(CurrencyRateEntity::getRate)
                .reduce(1.0, (a, b) -> a * b);
        return Math.pow(product, 1.0/workingRates.size());
    }

    private List<CurrencyRateEntity> getWorkingRates(final String code, final Integer year, final Integer month) {
        final LocalDate startDate = LocalDate.of(year, month, 1);
        final LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        final Set<LocalDate> workingDates = weekendsService.getWorkingDates(startDate, endDate);
        return repository.findAllByCodeAndDateBetween(code, startDate, endDate)
                .stream()
                .filter(e -> workingDates.contains(e.getDate()))
                .toList();
    }
}
