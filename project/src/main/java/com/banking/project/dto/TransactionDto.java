package com.banking.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    @NotNull
    private BigDecimal sentAmount;

    @NotNull
    private LocalDateTime issueDate;

    @NotBlank
    private String receiverIban;

    @NotBlank
    private String reason;
}
