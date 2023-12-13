package com.banking.project.repository;

import com.banking.project.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUserRepository extends JpaRepository<BankUser,Long> {
}
