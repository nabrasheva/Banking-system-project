package com.banking.project.repository;

import com.banking.project.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Long>, JpaSpecificationExecutor<BankUser> {

    Optional<BankUser> findBankUserByEmail(String email);
}
