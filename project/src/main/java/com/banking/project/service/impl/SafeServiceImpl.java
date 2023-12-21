package com.banking.project.service.impl;

import com.banking.project.dto.SafeDto;
import com.banking.project.entity.Safe;
import com.banking.project.exception.notfound.SafeNotFoundException;
import com.banking.project.repository.SafeRepository;
import com.banking.project.repository.specification.SafeSpecification;
import com.banking.project.service.SafeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.banking.project.constant.ExceptionMessages.SAFE_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class SafeServiceImpl implements SafeService {
    private final SafeRepository safeRepository;
    private final ModelMapper mapper;

    @Override
    public List<SafeDto> getAllSafes() {
        final List<Safe> safes = safeRepository.findAll();
        return safes.stream().map(safe -> mapper.map(safe, SafeDto.class)).toList();
    }

    @Override
    public SafeDto getSafeByName(final String name) {
        final Safe safe = safeRepository.findSafeByName(name).orElseThrow(() -> new SafeNotFoundException(SAFE_NOT_FOUND_MESSAGE));
        return mapper.map(safe, SafeDto.class);
    }

    @Override
    public Long createSafe(final Safe safe) {
        final Safe safeSaved = safeRepository.save(safe);
        return safeSaved.getId();
    }

    @Override
    public Long updateSafeByName(final SafeDto safeDto, final String name) {
        return null;
    }

    @Override
    public BigDecimal deleteSafe(final Safe safe) {
        final BigDecimal initialFunds = safe.getInitialFunds();
        final LocalDate creationDate = safe.getCreationDate();
        final BigDecimal yearsDifference = BigDecimal.valueOf(ChronoUnit.YEARS.between(creationDate, LocalDate.now()));

        final BigDecimal newFunds = yearsDifference.multiply(initialFunds).multiply(BigDecimal.valueOf(0.5));
        safeRepository.delete(safe);

        return newFunds;
    }

    @Override
    public boolean doesNameExist(final String name) {
        return safeRepository.exists(SafeSpecification.nameLike(name));
    }
}
