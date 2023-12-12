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

    @OneToOne(cascade = CascadeType.ALL)
    private DebitCard debitCard;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Safe> safes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_user_id")
    private BankUser bankUser;
}
