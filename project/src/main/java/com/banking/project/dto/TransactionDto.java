package com.banking.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotNull(message = "{sentAmount.NotNull}")
    private BigDecimal sentAmount;

    @NotNull(message = "{issueDate.NotNull}")
    private LocalDateTime issueDate;

    @Pattern(regexp = "[A-Z0-9]", message = "{receiverIban.Pattern}")
    private String receiverIban;

    @NotBlank(message = "{reason.NotBlank}")
    private String reason;
}
