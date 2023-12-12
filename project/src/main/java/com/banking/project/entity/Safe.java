package com.banking.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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

    @Column(name = "funds", nullable = false)
    private BigDecimal funds;

}
