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
public class LoanDto {

    @NotBlank(message = "{userIban.NotBlank}")
    private String userIban;

    @NotNull(message = "{creditAmount.NotNull}")
    private BigDecimal creditAmount;

}
