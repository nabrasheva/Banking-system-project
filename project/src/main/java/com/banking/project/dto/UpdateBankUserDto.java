package com.banking.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBankUserDto {
    @Size(min = 2, message = "{username.Size}")
    private String username;

    @Size(min = 3, max = 30)
    @Pattern(regexp = "^.{3,30}$", message = "{password.Pattern}")
    private String password;
}
