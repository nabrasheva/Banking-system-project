package com.banking.project.repository;

import com.banking.project.entity.Safe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafeRepository extends JpaRepository<Safe,Long> {
}
