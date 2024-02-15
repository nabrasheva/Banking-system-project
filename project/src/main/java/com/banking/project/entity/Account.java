package com.banking.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iban",nullable = false)
    private String iban;

    @Column(name = "available_amount",nullable = false)
    private BigDecimal availableAmount;

    @Column(name = "credit_amount")
    private BigDecimal creditAmount;

    @OneToOne(cascade = CascadeType.ALL)
    private DebitCard debitCard;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private List<Safe> safes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions;
    
}
