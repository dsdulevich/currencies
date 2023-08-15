package com.dds.currencies.api;

import com.dds.currencies.api.data.CurrencyRate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Сервис по получению информации по курсам валют")
public interface Currencies {
    @GetMapping("/currency/sync")
    @Operation(summary = "Добавление ежедневных курсов валют за заданный период в БД")
    @Parameters(value = {
            @Parameter(
                    name = "code",
                    description = "Код валюты",
                    required = true,
                    example = "USD",
                    schema = @Schema(implementation = String.class)
            ),
            @Parameter(
                    name = "startDate",
                    description = "Начальная дата",
                    required = true,
                    example = "2023-04-01",
                    schema = @Schema(implementation = LocalDate.class)
            ),
            @Parameter(
                    name = "endDate",
                    description = "Конечная дата",
                    required = true,
                    example = "2023-05-01",
                    schema = @Schema(implementation = LocalDate.class)
            )
    })
    List<CurrencyRate> syncCurrencyRates(@RequestParam("code") String code,
                                         @RequestParam("startDate") LocalDate startDate,
                                         @RequestParam("endDate") LocalDate endDate);

    @GetMapping("/currency")
    @Operation(summary = "Получение ежедневных курсов валют по заданному типу")
    @Parameters(value = {
            @Parameter(
                    name = "code",
                    description = "Код валюты",
                    required = true,
                    example = "USD",
                    schema = @Schema(implementation = String.class)
            )
    })
    List<CurrencyRate> getCurrencyRates(@RequestParam("code") String code);

    @GetMapping("/currency/average")
    @Operation(summary = "Получение среднего курса за месяц")
    @Parameters(value = {
            @Parameter(
                    name = "code",
                    description = "Код валюты",
                    required = true,
                    example = "USD",
                    schema = @Schema(implementation = String.class)
            ),
            @Parameter(
                    name = "year",
                    description = "Год",
                    required = true,
                    example = "2023",
                    schema = @Schema(implementation = Integer.class)
            ),
            @Parameter(
                    name = "month",
                    description = "Порядковый номер месяца",
                    required = true,
                    example = "4",
                    schema = @Schema(implementation = Integer.class)
            )
    })
    Double getAverageRate(@RequestParam("code") String code,
                          @RequestParam("year") Integer year,
                          @RequestParam("month") Integer month);
}
