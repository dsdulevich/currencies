package com.dds.currencies.validator;

import com.dds.currencies.exception.CurrencyValidationException;
import com.dds.currencies.service.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CurrencyValidator {

    private final CurrencyService currencyService;

    public void validate(final String code) {
        if (Objects.isNull(currencyService.getCurrencyIdByCode(code))) {
            throw new CurrencyValidationException("Unsupported currency code");
        }
    }
}
