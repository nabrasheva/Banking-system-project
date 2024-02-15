package com.banking.project.configuration.converters;

import com.banking.project.dto.BankUserDto;
import com.banking.project.entity.BankUser;
import org.modelmapper.AbstractConverter;

public class BankUserToDtoConverter extends AbstractConverter<BankUser, BankUserDto> {
    @Override
    protected BankUserDto convert(final BankUser bankUser) {
        return BankUserDto.builder()
                .username(bankUser.getUsername())
                .email(bankUser.getEmail())
                .country(bankUser.getCountry())
                .build();

    }
}
