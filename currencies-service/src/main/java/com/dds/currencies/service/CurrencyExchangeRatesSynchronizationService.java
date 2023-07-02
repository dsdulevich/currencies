package com.dds.currencies.service;

import com.dds.currencies.api.data.CurrencyRate;
import com.dds.currencies.entity.CurrencyRateEntity;
import com.dds.currencies.feign.nb.NationalBankClient;
import com.dds.currencies.feign.nb.dto.CurrencyRateNationalBankDto;
import com.dds.currencies.mapper.CurrencyRateMapper;
import com.dds.currencies.repository.CurrencyRateRepository;
import com.dds.currencies.service.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeRatesSynchronizationService {
    private final NationalBankClient client;
    private final CurrencyService currencyService;
    private final CurrencyRateRepository repository;
    private final CurrencyRateMapper mapper;

    public List<CurrencyRate> syncCurrencyRates(final String code, final LocalDate startDate, final LocalDate endDate) {
        if (isThisRangeInDatabase(code, startDate, endDate)) {
            return getFromDatabase(repository.findAllByCodeAndDateBetween(code, startDate, endDate).stream().toList());
        } else {
            return sync(code, startDate, endDate);
        }
    }

    private List<CurrencyRate> sync(final String code, final LocalDate startDate, final LocalDate endDate) {
        final Set<CurrencyRateEntity> ratesFromDatabase = repository.findAllByCodeAndDateBetween(code, startDate, endDate);
        final List<CurrencyRateEntity> ratesFromNationalBank = mapper.mapDtoToEntity(getRates(code, startDate, endDate), code);
        final List<CurrencyRateEntity> newRates = ratesFromNationalBank.stream().filter(e -> !ratesFromDatabase.contains(e)).toList();
        repository.saveAll(newRates);
        return mapper.map(newRates);
    }

    private List<CurrencyRateNationalBankDto> getRates(final String code, final LocalDate startDate, final LocalDate endDate) {
        return client.getCurrencyRates(currencyService.getCurrencyIdByCode(code), startDate.atStartOfDay(), endDate.atStartOfDay());
    }

    private boolean isThisRangeInDatabase(final String currencyId, final LocalDate startDate, final LocalDate endDate) {
        return repository.countByCodeAndDateBetween(currencyId, startDate, endDate) == countDaysBetween(startDate, endDate);
    }

    private long countDaysBetween(final LocalDate startDate, final LocalDate endDate) {
        return (endDate.toEpochDay() - startDate.toEpochDay()) + 1;
    }

    private List<CurrencyRate> getFromDatabase(final List<CurrencyRateEntity> repository) {
        return mapper.map(repository);
    }
}
