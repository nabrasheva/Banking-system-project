package com.banking.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "safe")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Safe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "key", nullable = false)
    private String key;

    @Column(name = "initial_funds", nullable = false)
    private BigDecimal initialFunds;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

}
