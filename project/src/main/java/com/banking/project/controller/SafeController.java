package com.banking.project.controller;

import com.banking.project.dto.SafeDto;
import com.banking.project.service.impl.SafeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/safes")
public class SafeController {
    private final SafeServiceImpl safeService;

    @GetMapping
    @ResponseStatus(value = OK)
    public List<SafeDto> getAllSafes() {
        return safeService.getAllSafes();
    }

    @GetMapping(params = "name")
    @ResponseStatus(value = OK)
    public SafeDto getAllSafes(@RequestParam(required = false) final String name) {
        return safeService.getSafeByName(name);
    }

    @PostMapping
    @ResponseStatus(value = CREATED)
    public Long createSafe(@RequestBody @Valid final SafeDto safeDto){
        return safeService.createSafe(safeDto);
    }
}
