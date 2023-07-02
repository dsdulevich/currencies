package com.dds.currencies.repository;

import com.dds.currencies.entity.WeekendEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeekendRepository extends JpaRepository<WeekendEntity, Long> {

    List<WeekendEntity> findAllByIsDayOffIsFalseAndDateBetween(LocalDate startDate, LocalDate endDate);
}
