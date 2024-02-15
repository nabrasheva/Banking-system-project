package com.banking.project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @NotBlank(message = "{iban.NotBlank}")
    @Size(max=34,message = "{iban.Size}")
    @Pattern(regexp = "[A-Z0-9]", message = "{iban.Pattern}")
    private String iban;

    @NotNull(message = "{availableAmount.NotNull}")
    @Min(0)
    private BigDecimal availableAmount;

    @Min(0)
    private BigDecimal creditAmount;

}
