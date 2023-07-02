package com.dds.currencies.feign.nb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CurrencyNationalBankDto {
    @JsonProperty("Cur_ID")
    private String currencyId;
    @JsonProperty("Cur_Abbreviation")
    private String code;
    @JsonProperty("Cur_DateEnd")
    private LocalDate endDate;
}
