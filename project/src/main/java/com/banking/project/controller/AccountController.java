package com.banking.project.controller;

import com.banking.project.dto.AccountDto;
import com.banking.project.dto.LoanDto;
import com.banking.project.dto.DebitCardDto;
import com.banking.project.dto.SafeDto;
import com.banking.project.dto.TransactionDto;
import com.banking.project.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/{iban}/safe")
    @ResponseStatus(value = CREATED)
    public SafeDto createAccountSafe(@PathVariable final String iban, @RequestBody @Valid final SafeDto safeDto) {
        return accountService.createSafeForAccount(iban, safeDto);
    }

    @GetMapping(params = "iban")
    @ResponseStatus(value = OK)
    public AccountDto getByIban(@RequestParam final String iban) {
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

    @GetMapping("/{iban}/card")
    @ResponseStatus(value = OK)
    public DebitCardDto getAccountDebiCard(@PathVariable final String iban) {
        return accountService.getDebitCardByIban(iban);
    }

    @PatchMapping(value = "/{iban}/safe", params = {"name", "funds"})
    @ResponseStatus(value = CREATED)
    public void updateAccountSafe(@PathVariable final String iban, @RequestParam final String name, @RequestParam final BigDecimal funds) {
        accountService.updateSafe(iban, name, funds);
    }

    @PostMapping("/{iban}/transaction")
    @ResponseStatus(value = CREATED)
    public TransactionDto sendMoney(@PathVariable final String iban, @RequestBody @Valid final TransactionDto transactionDto) {
        return accountService.sendMoney(iban, transactionDto);
    }

    @PostMapping("/take-loan")
    @ResponseStatus(value = CREATED)
    public TransactionDto takeLoan(@RequestBody final LoanDto loanDto) {
        return accountService.takeLoan(loanDto);
    }

    @PostMapping("/return-loan")
    @ResponseStatus(value = CREATED)
    public TransactionDto returnLoan(@RequestBody final LoanDto loanDto) {
        return accountService.returnLoan(loanDto);
    }


}
