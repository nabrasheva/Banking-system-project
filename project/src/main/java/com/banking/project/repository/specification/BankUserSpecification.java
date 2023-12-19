package com.banking.project.repository.specification;


import com.banking.project.entity.BankUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankUserSpecification {
    public static Specification<BankUser> emailLike(final String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }

}
