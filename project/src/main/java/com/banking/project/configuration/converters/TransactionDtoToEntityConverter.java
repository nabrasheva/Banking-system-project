package com.banking.project.configuration.converters;

import com.banking.project.dto.TransactionDto;
import com.banking.project.entity.Transaction;
import org.modelmapper.AbstractConverter;

import java.time.LocalDateTime;

public class TransactionDtoToEntityConverter extends AbstractConverter<TransactionDto, Transaction> {

    @Override
    protected Transaction convert(final TransactionDto transactionDto) {
        final Transaction transaction = Transaction.builder()
                .issueDate(LocalDateTime.now())
                .sentAmount(transactionDto.getSentAmount())
                .receiverIban(transactionDto.getReceiverIban())
                .reason(transactionDto.getReason())
                .creditPayment(transactionDto.isCreditPayment())
                .build();
        final String receiverIban = transactionDto.getReceiverIban();
        if (receiverIban !=  null && !receiverIban.isBlank())
        {
            transaction.setReceiverIban(receiverIban);
        }
        return transaction;
    }
}
