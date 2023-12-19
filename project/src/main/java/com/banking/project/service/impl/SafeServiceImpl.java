package com.banking.project.service.impl;

import com.banking.project.dto.SafeDto;
import com.banking.project.entity.Safe;
import com.banking.project.repository.SafeRepository;
import com.banking.project.repository.specification.SafeSpecification;
import com.banking.project.service.SafeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
        final Safe safe = safeRepository.findSafeByName(name).orElseThrow();
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
    public void deleteSafeByName(final String name) {

    }

    @Override
    public boolean doesNameExist(final String name) {
        return safeRepository.exists(SafeSpecification.nameLike(name));
    }
}
