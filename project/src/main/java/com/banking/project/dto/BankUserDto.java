package com.banking.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankUserDto {

    @Size(min=2)
    @NotBlank
    private String username;

    @Size(min=3, max=30)
    @NotBlank
    private String password;

    @Size(min=2)
    @NotBlank
    private String country;

}
