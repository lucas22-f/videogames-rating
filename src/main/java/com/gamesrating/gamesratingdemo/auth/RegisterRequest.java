package com.gamesrating.gamesratingdemo.auth;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "se necesita el nombre")
    @Size(min = 5, max = 20, message = "mas de 5 caracteres y menos de 20 caracteres")
    String nombre;
    @Email(message = "ingrese mail valido")
    @NotBlank(message = "se necesita email.")
    String email;
    @NotBlank(message = "se necesita password.")
    String password;
}
