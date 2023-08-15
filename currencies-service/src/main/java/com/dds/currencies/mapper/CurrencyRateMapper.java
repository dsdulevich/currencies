package com.dds.currencies.mapper;

import com.dds.currencies.entity.CurrencyRateEntity;
import com.dds.currencies.feign.nb.dto.CurrencyRateNationalBankDto;
import com.dds.currencies.api.data.CurrencyRate;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CurrencyRateMapper {

    List<CurrencyRate> mapToDto(List<CurrencyRateEntity> rate);

    List<CurrencyRateEntity> mapToEntity(List<CurrencyRate> rate);
}
