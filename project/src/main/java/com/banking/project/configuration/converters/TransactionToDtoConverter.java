package com.banking.project.configuration.converters;

import com.banking.project.dto.TransactionDto;
import com.banking.project.entity.Transaction;
import org.modelmapper.AbstractConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TransactionToDtoConverter extends AbstractConverter<Transaction, TransactionDto> {
    @Override
    protected TransactionDto convert(Transaction transaction) {
        return TransactionDto.builder()
                .sentAmount(transaction.getSentAmount())
                .receiverIban(transaction.getReceiverIban())
                .reason(transaction.getReason())
                .issueDate(convertDataTime(transaction.getIssueDate()))
                .build();
    }

    private long convertDataTime(LocalDateTime date) {
        return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
