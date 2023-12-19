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

    @NotBlank(message = "{email.NotBlank}")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "{email.Pattern}")
    private String email;

    @Size(min = 3, max = 30)
    @NotBlank(message = "{password.NotBlank}")
    @Pattern(regexp = "^.{3,30}$", message = "{password.Pattern}")
    private String password;

    @Size(max = 2, message = "{country.Size}")
    @NotBlank(message = "{country.NotBlank}")
    private String country;

}
