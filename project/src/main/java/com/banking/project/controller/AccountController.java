package com.banking.project.controller;

import com.banking.project.dto.SafeDto;
import com.banking.project.service.impl.AccountServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountServiceImpl accountService;

    @PostMapping("/{id}/safe")
    @ResponseStatus(value = CREATED)
    public String createAccountSafe(@PathVariable final Long id, @RequestBody @Valid final SafeDto safeDto) {
        return "Your safe id is :  " + accountService.createSafeForAccount(id, safeDto);
    }
}
