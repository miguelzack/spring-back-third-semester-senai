package com.dm.biblioteca.DTOs;

import com.dm.biblioteca.entity.Loan;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class UserRequestDTO {
    @NotBlank(message = "O campo não pode estar vazio.")
    private String nome;
    @NotBlank(message = "O campo não pode estar vazio.")
    @Email
    private String email;
    private List<Loan> loans = new ArrayList<>();

    public UserRequestDTO(String nome, String email, List<Loan> loans) {
        this.nome = nome;
        this.email = email;
        this.loans = loans;
    }
}
