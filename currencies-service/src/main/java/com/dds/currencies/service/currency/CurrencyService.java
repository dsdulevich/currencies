package com.dds.currencies.service.currency;

import com.dds.currencies.entity.CurrencyEntity;
import com.dds.currencies.feign.nb.NationalBankService;
import com.dds.currencies.feign.nb.dto.CurrencyNationalBankDto;
import com.dds.currencies.mapper.CurrencyMapper;
import com.dds.currencies.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final NationalBankService nationalBankService;
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    private Map<String, String> currencies = new HashMap<>();

    @PostConstruct
    public void init() {
        updateCurrencies();
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void updateCurrencies() {
        final List<CurrencyNationalBankDto> currenciesList = nationalBankService.getCurrencies();

        if (currenciesList.isEmpty() && currencies.isEmpty()) {
            currencies = currencyRepository.findAll()
                    .stream()
                    .collect(Collectors.toMap(CurrencyEntity::getCode, CurrencyEntity::getId));
        } else if (!currenciesList.isEmpty()) {
            final Map<String, String> currenciesFromNationalBank = currenciesList
                    .stream()
                    .collect(Collectors.toMap(CurrencyNationalBankDto::getCode, CurrencyNationalBankDto::getCurrencyId));
            if (!currenciesFromNationalBank.equals(currencies)) {
                currencyRepository.deleteAll();
                currencyRepository.saveAll(currencyMapper.map(currenciesList));

                currencies = currenciesFromNationalBank;
            }
        }
    }

    public String getCurrencyIdByCode(final String code) {
        return currencies.get(code);
    }
}
