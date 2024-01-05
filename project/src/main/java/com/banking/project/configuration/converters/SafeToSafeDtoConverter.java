package com.banking.project.configuration.converters;

import com.banking.project.dto.SafeDto;
import com.banking.project.entity.Safe;
import org.modelmapper.AbstractConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class SafeToSafeDtoConverter extends AbstractConverter<Safe, SafeDto> {
    @Override
    protected SafeDto convert(Safe source) {
        LocalDate now = LocalDate.now();
        BigDecimal yearsBetween = BigDecimal.valueOf(ChronoUnit.YEARS.between(source.getCreationDate(), now));
        BigDecimal newFunds;

        if (yearsBetween.equals(BigDecimal.ZERO)) {
            newFunds = source.getInitialFunds();
        }
        else {
            BigDecimal annualInterestRate = BigDecimal.valueOf(0.01);
            BigDecimal ratePlusOne = BigDecimal.ONE.add(annualInterestRate);
            newFunds = source.getInitialFunds().multiply(ratePlusOne.pow(yearsBetween.intValue()));
        }
        return SafeDto
                .builder()
                .initialFunds(newFunds)
                .name(source.getName())
                .key(source.getKey())
                .build();
    }
}
