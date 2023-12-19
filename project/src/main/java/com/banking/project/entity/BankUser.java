package com.banking.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bank_user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="country", nullable = false)
    private String country;

    @OneToOne
    private Account account;
}
