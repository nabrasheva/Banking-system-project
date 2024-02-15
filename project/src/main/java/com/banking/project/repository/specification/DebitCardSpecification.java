package com.banking.project.repository.specification;

import com.banking.project.entity.DebitCard;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DebitCardSpecification {
    public static Specification<DebitCard> numberLike(final String number) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("number"), "%" + number + "%");
    }
}
