package com.dds.currencies.service.weekend;

import com.dds.currencies.entity.WeekendEntity;
import com.dds.currencies.repository.WeekendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeekendsService {
    private final WeekendRepository weekendRepository;

    public Set<LocalDate> getWorkingDates(final LocalDate startDate, final LocalDate endDate) {
        return weekendRepository.findAllByIsDayOffIsFalseAndDateBetween(startDate, endDate)
                .stream()
                .map(WeekendEntity::getDate)
                .collect(Collectors.toSet());
    }
}
