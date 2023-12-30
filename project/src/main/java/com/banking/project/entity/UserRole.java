package com.banking.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String type;
}
