package com.banking.project.repository;

import com.banking.project.entity.Safe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SafeRepository extends JpaRepository<Safe, Long>, JpaSpecificationExecutor<Safe> {
    Optional<Safe> findSafeByName(String name);
}
