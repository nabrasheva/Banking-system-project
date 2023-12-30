package com.banking.project.controller;

import com.banking.project.dto.BankUserDto;
import com.banking.project.dto.LoginRequest;
import com.banking.project.dto.LoginResponse;
import com.banking.project.dto.UpdateBankUserDto;
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

    @PostMapping("auth/registration")
    @ResponseStatus(value = CREATED)
    public LoginResponse createUser(@Valid @RequestBody final BankUserDto bankUserDto) throws MailjetSocketTimeoutException, MailjetException {
        return bankUserService.createBankUser(bankUserDto);
    }

    @PostMapping("auth/login")
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
}
