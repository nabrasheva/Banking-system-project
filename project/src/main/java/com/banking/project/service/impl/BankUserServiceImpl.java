package com.banking.project.service.impl;

import com.banking.project.dto.BankUserDto;
import com.banking.project.dto.UpdateBankUserDto;
import com.banking.project.entity.Account;
import com.banking.project.entity.BankUser;
import com.banking.project.entity.DebitCard;
import com.banking.project.exception.exists.UserAlreadyExistsException;
import com.banking.project.exception.notfound.UserNotFoundException;
import com.banking.project.repository.BankUserRepository;
import com.banking.project.repository.specification.BankUserSpecification;
import com.banking.project.service.AccountService;
import com.banking.project.service.BankUserService;
import com.banking.project.service.DebitCardService;
import com.banking.project.service.EmailSenderService;
import com.banking.project.utils.CVVGenerator;
import com.banking.project.utils.DebitCardNumberGenerator;
import com.banking.project.utils.IbanGenerator;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.banking.project.constant.ExceptionMessages.USER_ALREADY_EXISTS_MESSAGE;
import static com.banking.project.constant.ExceptionMessages.USER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class BankUserServiceImpl implements BankUserService {

    private final AccountService accountService;
    private final BankUserRepository bankUserRepository;
    private final ModelMapper modelMapper;
    private final DebitCardService debitCardService;
    private final EmailSenderService emailSenderService;
    // private final PasswordEncoder passwordEncoder;

    @Override
    public Long createBankUser(final BankUserDto bankUserDto) throws MailjetSocketTimeoutException, MailjetException {
        if (bankUserRepository.exists(BankUserSpecification.emailLike(bankUserDto.getEmail()))) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS_MESSAGE);
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

        final Account account = Account.builder().availableAmount(BigDecimal.valueOf(0)).iban(iban).debitCard(debitCard).build();

        final BankUser user = BankUser.builder().email(bankUserDto.getEmail())
                .country(bankUserDto.getCountry())
                .username(bankUserDto.getUsername())
                .password(bankUserDto.getPassword())
                .account(account)
                .build();
        bankUserRepository.save(user);
        emailSenderService.sendRegistrationConfirmationEmail(user);


        return user.getId();
    }

    @Override
    public void deleteBankUser(final String email) {
        final BankUser bankUser = bankUserRepository.findBankUserByEmail(email).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
        bankUserRepository.delete(bankUser);
    }

    @Override
    public BankUserDto getUserByEmail(final String email) {
        final BankUser user = bankUserRepository.findBankUserByEmail(email).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
        return modelMapper.map(user, BankUserDto.class);
    }

    @Override
    public void updateUsernameAndPassword(final String email, final UpdateBankUserDto bankUser) {
        final BankUser user = bankUserRepository.findBankUserByEmail(email).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));

        user.setUsername(bankUser.getUsername());
        user.setPassword(bankUser.getPassword());

        bankUserRepository.save(user);
    }


}
