package com.banking.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SafeDto {

    @NotBlank
    private String name;

    @NotBlank
    private String key;

    @NotNull
    private BigDecimal initialFunds;

}
