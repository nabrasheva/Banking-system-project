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
public class LoanDto {

    @NotBlank(message = "{userIban.NotBlank}")
    private String userIban;

    @NotNull(message = "{creditAmount.NotNull}")
    @Positive
    private BigDecimal creditAmount;

}
