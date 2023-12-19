package com.banking.project.service.impl;

import com.banking.project.dto.BankUserDto;
import com.banking.project.entity.Account;
import com.banking.project.entity.BankUser;
import com.banking.project.entity.DebitCard;
import com.banking.project.repository.BankUserRepository;
import com.banking.project.repository.specification.BankUserSpecification;
import com.banking.project.service.AccountService;
import com.banking.project.service.BankUserService;
import com.banking.project.service.DebitCardService;
import com.banking.project.utils.CVVGenerator;
import com.banking.project.utils.DebitCardNumberGenerator;
import com.banking.project.utils.IbanGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BankUserServiceImpl implements BankUserService {

    private final AccountService accountService;
    private final BankUserRepository bankUserRepository;
    private final ModelMapper modelMapper;
    private final DebitCardService debitCardService;
    // private final PasswordEncoder passwordEncoder;

    @Override
    public Long createBankUser(final BankUserDto bankUserDto) {
        if (bankUserRepository.exists(BankUserSpecification.emailLike(bankUserDto.getEmail()))) {
            throw new IllegalArgumentException("Bank user already exists");
        }
        String iban = IbanGenerator.generateIban(bankUserDto.getCountry());
        while (accountService.doesIbanExist(iban)) {
            iban = IbanGenerator.generateIban(bankUserDto.getCountry());
        }
        String number = DebitCardNumberGenerator.generateDebitCardNumber();
        while (debitCardService.doesNumberExist(number)) {
            number = DebitCardNumberGenerator.generateDebitCardNumber();
        }
        final DebitCard debitCard = DebitCard.builder().cvv(CVVGenerator.generateCVV()).expiryDate(LocalDate.now().plusYears(3)).number(number).build();

        debitCardService.saveDebitCard(debitCard);
        final Account account = Account.builder().availableAmount(BigDecimal.valueOf(0)).iban(iban).debitCard(debitCard).build();

        accountService.saveAccount(account);
        final BankUser user = BankUser.builder().email(bankUserDto.getEmail())
                .country(bankUserDto.getCountry())
                .username(bankUserDto.getUsername())
                .password(bankUserDto.getPassword())
                .account(account)
                .build();
        bankUserRepository.save(user);

        return user.getId();
    }
}
