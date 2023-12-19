package com.banking.project.service;

import com.banking.project.dto.SafeDto;

import java.util.List;

public interface SafeService {
    List<SafeDto> getAllSafes();

    SafeDto getSafeByName(String name);

    Long createSafe(SafeDto safeDto);

    Long updateSafeByName(SafeDto safeDto, String name);

    void deleteSafeByName(String name);

}
