package com.banking.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DebitCardDto {

    @NotBlank(message = "{number.NotBlank}")
    private String number;

    @NotNull(message = "{expiryDate.NotNull}")
    private LocalDate expiryDate;

    @NotNull(message = "{cvv.NotNull}")
    private Long cvv;

}
