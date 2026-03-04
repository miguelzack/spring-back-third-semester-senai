package com.dm.ecommerce.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LoginRequestDTO {

    @NotBlank(message = "O campo não pode estar vazio.")
    @Email(message = "Digite um email válido")
    private String email;
    @NotBlank(message = "O campo não pode estar vazio.")
    private String senha;

    public LoginRequestDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
}
