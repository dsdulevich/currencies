package com.dds.currencies.repository;

import com.dds.currencies.entity.CurrencyEntity;
import com.dds.currencies.entity.CurrencyRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, String> {
}
