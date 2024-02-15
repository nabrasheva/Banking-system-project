package com.banking.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    @NotNull(message = "{sentAmount.NotNull}")
    private BigDecimal sentAmount;

    @Pattern(regexp = "^[A-Z0-9]+$", message = "{receiverIban.Pattern}")
    private String receiverIban;

    @NotBlank(message = "{reason.NotBlank}")
    private String reason;

    @Value("false")
    private boolean creditPayment;

    private Long issueDate;
}
