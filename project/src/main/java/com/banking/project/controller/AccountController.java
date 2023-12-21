package com.banking.project.controller;

import com.banking.project.dto.AccountDto;
import com.banking.project.dto.SafeDto;
import com.banking.project.dto.TransactionDto;
import com.banking.project.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/{iban}/safe")
    @ResponseStatus(value = CREATED)
    public String createAccountSafe(@PathVariable final String iban, @RequestBody @Valid final SafeDto safeDto) {
        return "Your safe id is :  " + accountService.createSafeForAccount(iban, safeDto);
    }

    @GetMapping(params = "iban")
    @ResponseStatus(value = OK)
    public AccountDto getById(@RequestParam final String iban) {
        return accountService.getAccountByIban(iban);
    }

    @DeleteMapping("/{iban}/{name}")
    @ResponseStatus(value = NO_CONTENT)
    public void deleteAccountSafe(@PathVariable final String iban, @PathVariable final String name) {
        accountService.deleteSafeByNameAndIban(name, iban);
    }

    @GetMapping("/{iban}/transaction")
    @ResponseStatus(value = OK)
    public List<TransactionDto> getAccountTransactions(@PathVariable final String iban) {
        return accountService.getAccountTransactions(iban);
    }

    @GetMapping("/{iban}/safe")
    @ResponseStatus(value = OK)
    public List<SafeDto> getAccountSafes(@PathVariable final String iban) {
        return accountService.getAccountSafes(iban);
    }

}
