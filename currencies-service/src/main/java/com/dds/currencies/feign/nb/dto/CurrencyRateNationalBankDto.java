package com.dds.currencies.feign.nb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CurrencyRateNationalBankDto {
    @JsonProperty("Cur_ID")
    private String currencyId;
    @JsonProperty("Cur_OfficialRate")
    private Double rate;
    @JsonProperty("Date")
    private LocalDate date;
}
