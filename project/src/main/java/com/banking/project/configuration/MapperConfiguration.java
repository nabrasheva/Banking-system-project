package com.banking.project.configuration;

import com.banking.project.configuration.converters.BankUserToDtoConverter;
import com.banking.project.configuration.converters.TransactionDtoToEntityConverter;
import com.banking.project.configuration.converters.TransactionToDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper mapper = new ModelMapper();
        mapper.addConverter(new TransactionDtoToEntityConverter());
        mapper.addConverter(new BankUserToDtoConverter());
        mapper.addConverter(new TransactionToDtoConverter());
        return mapper;
    }
}
