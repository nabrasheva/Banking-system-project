package com.banking.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DebitCardDto {

    @NotBlank(message = "{number.NotBlank}")
    @Size(max = 16, message = "{number.Size}")
    private String number;

    @NotNull(message = "{expiryDate.NotNull}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    @NotNull(message = "{cvv.NotNull}")
    private Long cvv;

}
