package com.dds.currencies.repository;

import com.dds.currencies.entity.CurrencyRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRateEntity, Long> {

    long countByCodeAndDateBetween(String id, LocalDate startDate, LocalDate endDate);

    List<CurrencyRateEntity> findAllByCode(String id);

    Set<CurrencyRateEntity> findAllByCodeAndDateBetween(String id, LocalDate startDate, LocalDate endDate);
}
