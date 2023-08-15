package com.dds.currencies.mapper;

import com.dds.currencies.entity.CurrencyEntity;
import com.dds.currencies.feign.nb.dto.CurrencyNationalBankDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    @Mapping(target = "id", source = "currencyId")
    List<CurrencyEntity> map(List<CurrencyNationalBankDto> rate);
}
