package com.dds.currencies.api.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Информация по курсу валюты")
public class CurrencyRate {
    @Schema(description = "Буквенный код валюты")
    private String code;
    @Schema(description = "Курс")
    private Double rate;
    @Schema(description = "Дата")
    private LocalDate date;
}
