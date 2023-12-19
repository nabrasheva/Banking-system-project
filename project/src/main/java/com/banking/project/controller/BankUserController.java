package com.banking.project.controller;

import com.banking.project.dto.BankUserDto;
import com.banking.project.service.BankUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class BankUserController {
    private final BankUserService bankUserService;

    @PostMapping()
    @ResponseStatus(value = CREATED)
    public Long createUser(@Valid @RequestBody final BankUserDto bankUserDto)
    {
        return bankUserService.createBankUser(bankUserDto);
    }
}
