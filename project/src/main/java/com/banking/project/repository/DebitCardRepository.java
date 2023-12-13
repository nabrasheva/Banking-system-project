package com.banking.project.repository;

import com.banking.project.entity.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard,Long> {
}
