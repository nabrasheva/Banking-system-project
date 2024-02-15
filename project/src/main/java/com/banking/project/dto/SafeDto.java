package com.banking.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotBlank(message = "{name.NotBlank}")
    private String name;

    @NotBlank(message = "{key.NotBlank}")
    private String key;

    @NotNull(message = "{initialFunds.NotNull}")
    @Positive
    private BigDecimal initialFunds;

}
