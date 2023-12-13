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
public class BankUserDto {

    @Size(min = 2, message = "{username.Size}")
    @NotBlank(message = "{username.NotBlank}")
    private String username;

    @Size(min = 3, max = 30)
    @NotBlank(message = "{password.NotBlank}")
    @Pattern(regexp = "^.{3,30}$", message = "{password.Pattern}")
    private String password;

    @Size(min = 2, message = "{country.Size}")
    @NotBlank(message = "{country.NotBlank}")
    private String country;

}
