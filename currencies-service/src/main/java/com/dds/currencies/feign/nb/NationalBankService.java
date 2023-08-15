package com.dds.currencies.feign.nb;

import com.dds.currencies.feign.nb.dto.CurrencyNationalBankDto;
import com.dds.currencies.feign.nb.dto.CurrencyRateNationalBankDto;
import com.dds.currencies.service.util.DateUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NationalBankService {
    private final NationalBankClient client;
    private final DateUtilService dateUtilService;

    public List<CurrencyNationalBankDto> getCurrencies() {
        final LocalDate now = dateUtilService.getCurrentDate();
        try {
            return client.getCurrencies()
                    .stream()
                    .filter(c -> c.getEndDate().isAfter(now))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<CurrencyRateNationalBankDto> getCurrencyRates(String id, LocalDateTime startDate, LocalDateTime endDate) {
        return client.getCurrencyRates(id, startDate, endDate);
    }
}
