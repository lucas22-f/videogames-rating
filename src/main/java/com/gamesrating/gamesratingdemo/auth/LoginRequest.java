package com.gamesrating.gamesratingdemo.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @Email(message = "Ingresa un Email Valido")
    @NotBlank(message = "Se necesita el Email.")
    String email;
    @NotBlank(message = "Se necesita password")
    String password;
}
