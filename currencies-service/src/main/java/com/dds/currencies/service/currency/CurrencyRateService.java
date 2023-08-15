package com.dds.currencies.service.currency;

import com.dds.currencies.api.data.CurrencyRate;
import com.dds.currencies.entity.CurrencyRateEntity;
import com.dds.currencies.exception.CurrencyNotFoundException;
import com.dds.currencies.mapper.CurrencyRateMapper;
import com.dds.currencies.repository.CurrencyRateRepository;
import com.dds.currencies.service.weekend.WeekendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {
    private final CurrencyRateRepository currencyRateRepository;
    private final CurrencyRateMapper currencyRateMapper;
    private final WeekendsService weekendsService;

    public List<CurrencyRate> findAllByCode(final String code){
        return currencyRateMapper.mapToDto(currencyRateRepository.findAllByCode(code));
    }

    public Double getAverageRate(final String code, final LocalDate startDate, final LocalDate endDate){
        final Set<LocalDate> workingDates = weekendsService.getWorkingDates(startDate, endDate);

        final List<CurrencyRateEntity> workingRates = currencyRateRepository.findAllByCodeAndDateBetween(code, startDate, endDate)
                .stream()
                .filter(e -> workingDates.contains(e.getDate()))
                .toList();

        if (workingRates.isEmpty()) {
            throw new CurrencyNotFoundException("There are no exchange rates for this month");
        }

        final Double product = workingRates
                .stream()
                .map(CurrencyRateEntity::getRate)
                .reduce(1.0, (a, b) -> a * b);
        return Math.pow(product, 1.0 / workingRates.size());
    }

    public long countByCodeAndDateBetween(final String code, final LocalDate startDate, final LocalDate endDate){
        return currencyRateRepository.countByCodeAndDateBetween(code, startDate, endDate);
    }

    public List<CurrencyRate> findAllByCodeAndDateBetween(final String code, final LocalDate startDate, final LocalDate endDate){
        return currencyRateMapper.mapToDto(currencyRateRepository.findAllByCodeAndDateBetween(code, startDate, endDate).stream().toList());
    }

    public void saveAll(final List<CurrencyRate> rate){
        currencyRateRepository.saveAll(currencyRateMapper.mapToEntity(rate));
    }
}
