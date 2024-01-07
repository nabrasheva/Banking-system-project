package com.banking.project.controller;

import com.banking.project.dto.*;
import com.banking.project.service.BankUserService;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class BankUserController {
    private final BankUserService bankUserService;

    @PostMapping("/registration")
    @ResponseStatus(value = CREATED)
    public String createUser(@Valid @RequestBody final BankUserDto bankUserDto) throws MailjetSocketTimeoutException, MailjetException {
        bankUserService.registration(bankUserDto);
        return "User created successfully";
    }

    @PostMapping("/login")
    @ResponseStatus(value = OK)
    public LoginResponse login(@RequestBody @Valid final LoginRequest loginRequest) {
        return bankUserService.login(loginRequest);
    }


    @GetMapping(params = "email")
    @ResponseStatus(value = OK)
    public BankUserDto getUserByEmail(@RequestParam final String email) {
        return bankUserService.getUserByEmail(email);
    }

    @DeleteMapping(value = "/{email}")
    @ResponseStatus(value = NO_CONTENT)
    public void deleteUser(@PathVariable final String email) {
        bankUserService.deleteBankUser(email);
    }

    @PatchMapping(value = "/{email}")
    @ResponseStatus(value = NO_CONTENT)
    public void updateUser(@PathVariable final String email, @RequestBody final UpdateBankUserDto bankUser) {
        bankUserService.updateUsernameAndPassword(email, bankUser);
    }

    @GetMapping(value = "/account",params = "email")
    @ResponseStatus(value = OK)
    public AccountDto getAccountByEmail(@RequestParam final String email) {
        return bankUserService.getAccountByEmail(email);
    }

    @PostMapping(value = "/create-admin")
    @ResponseStatus(value = CREATED)
    public void createAdmin(@RequestBody @Valid final BankUserDto bankUserDto) {
        bankUserService.createAdmin(bankUserDto);
    }
}
