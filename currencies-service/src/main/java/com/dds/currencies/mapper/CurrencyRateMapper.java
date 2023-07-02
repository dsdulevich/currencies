package com.dds.currencies.mapper;

import com.dds.currencies.entity.CurrencyRateEntity;
import com.dds.currencies.feign.nb.dto.CurrencyRateNationalBankDto;
import com.dds.currencies.api.data.CurrencyRate;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CurrencyRateMapper {

    List<CurrencyRate> map(List<CurrencyRateEntity> rate);

    default List<CurrencyRateEntity> mapDtoToEntity(List<CurrencyRateNationalBankDto> rates, String code) {
        return rates.stream().map(r -> new CurrencyRateEntity()
                        .setCode(code)
                        .setDate(r.getDate())
                        .setRate(r.getRate()))
                .collect(Collectors.toList());
    }
}
