package com.banking.project.controller;

import com.banking.project.dto.AccountDto;
import com.banking.project.dto.SafeDto;
import com.banking.project.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/{id}/safe")
    @ResponseStatus(value = CREATED)
    public String createAccountSafe(@PathVariable final Long id, @RequestBody @Valid final SafeDto safeDto) {
        return "Your safe id is :  " + accountService.createSafeForAccount(id, safeDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = OK)
    public AccountDto getById(@PathVariable final Long id) {
        return accountService.getAccountById(id);
    }

    @DeleteMapping("/{iban}/{name}")
    @ResponseStatus(value = NO_CONTENT)
    public void deleteAccountSafe(@PathVariable final String iban, @PathVariable final String name) {
        accountService.deleteSafeByNameAndIban(name, iban);
    }
}
