package com.banking.project.controller;

import com.banking.project.dto.SafeDto;
import com.banking.project.service.impl.SafeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/safes")
@Slf4j
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

}
