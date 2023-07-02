package com.dds.currencies.feign.nb;

import com.dds.currencies.feign.nb.dto.CurrencyNationalBankDto;
import com.dds.currencies.feign.nb.dto.CurrencyRateNationalBankDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(value = "national-bank", url = "https://api.nbrb.by")
public interface NationalBankClient {

    @RequestMapping(method = RequestMethod.GET, value = "/exrates/currencies/")
    List<CurrencyNationalBankDto> getCurrencies();

    @RequestMapping(method = RequestMethod.GET, value = "/exrates/rates/dynamics/{cur_id}")
    List<CurrencyRateNationalBankDto> getCurrencyRates(@PathVariable("cur_id") String curId,
                                                       @RequestParam("startDate") @DateTimeFormat(pattern = "MM-dd-yyyy, HH:mm") LocalDateTime startDate,
                                                       @RequestParam("endDate") @DateTimeFormat(pattern = "MM-dd-yyyy, HH:mm") LocalDateTime endDate);
}
