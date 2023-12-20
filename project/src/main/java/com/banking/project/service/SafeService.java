package com.banking.project.service;

import com.banking.project.dto.SafeDto;
import com.banking.project.entity.Safe;

import java.util.List;

public interface SafeService {
    List<SafeDto> getAllSafes();

    SafeDto getSafeByName(String name);

    Long createSafe(Safe safe);

    Long updateSafeByName(SafeDto safeDto, String name);

    void deleteSafeByName(Long id);

    boolean doesNameExist(String name);

}
