package com.banking.project.repository;

import com.banking.project.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser,Long> {
}
