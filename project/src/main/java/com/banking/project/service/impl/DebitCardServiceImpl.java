package com.banking.project.service.impl;

import com.banking.project.entity.DebitCard;
import com.banking.project.repository.DebitCardRepository;
import com.banking.project.repository.specification.DebitCardSpecification;
import com.banking.project.service.DebitCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DebitCardServiceImpl implements DebitCardService {
    private final DebitCardRepository debitCardRepository;

    @Override
    public boolean doesNumberExist(final String number) {
        return debitCardRepository.exists(DebitCardSpecification.numberLike(number));
    }

    @Override
    public Long saveDebitCard(final DebitCard debitCard) {
        final DebitCard debitCardSaved = debitCardRepository.save(debitCard);
        return debitCardSaved.getId();
    }
}
