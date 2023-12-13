package com.banking.project.repository;

import com.banking.project.entity.Safe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SafeRepository extends JpaRepository<Safe,Long> {
}
