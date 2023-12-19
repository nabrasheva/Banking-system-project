package com.banking.project.repository.specification;

import com.banking.project.entity.Account;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountSpecification {
    public static Specification<Account> ibanLike(final String iban) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("iban"), "%" + iban + "%");
    }
}
