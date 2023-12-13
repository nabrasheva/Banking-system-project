package com.banking.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    private BigDecimal availableAmount;

}
