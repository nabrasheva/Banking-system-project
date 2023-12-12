package com.banking.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="sent_amount", nullable = false)
    private BigDecimal sentAmount;

    @Column(name="issue_date", nullable = false)
    private LocalDateTime issueDate;

    @Column(name="receiver_iban", nullable = false)
    private String receiverIban;

    @Column(name="reason", nullable = false)
    private String reason;

}
