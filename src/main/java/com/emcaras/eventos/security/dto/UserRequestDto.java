package com.emcaras.eventos.security.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "El name no debe ir vacio")
    @Size(min = 3, max = 50, message = "El name debe tener entre 3 y 50 caracteres")
    private String name;

    @NotBlank(message = "El username no debe ir vacio")
    @Size(min = 3, max = 15, message = "El username debe tener entre 3 y 15 caracteres")
    private String username;

    @NotBlank(message = "El email no puede ir vacio")
    @Email(message = "Debe ser un email valido")
    private String email;

    @NotBlank(message = "El password no debe ir vacio")
    @Size(min = 3, max = 50, message = "El password debe tener entre 3 y 50 caracteres")
    private String password;

    private Set<String> roles;
}
