package com.dds.currencies.service.currency;

import com.dds.currencies.feign.nb.NationalBankClient;
import com.dds.currencies.feign.nb.dto.CurrencyNationalBankDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private Map<String, String> currencies = new HashMap<>();
    private final NationalBankClient client;

    @PostConstruct
    public void init() {
        updateCurrencies();
    }

    @Scheduled(cron = "0 1 * * * ?")
    public void updateCurrencies() {
        final LocalDate now = LocalDate.now();
        currencies = client.getCurrencies()
                .stream()
                .filter(c -> c.getEndDate().isAfter(now))
                .collect(Collectors.toMap(CurrencyNationalBankDto::getCode, CurrencyNationalBankDto::getCurrencyId));
    }

    public String getCurrencyIdByCode(final String code) {
        return currencies.get(code);
    }
}
