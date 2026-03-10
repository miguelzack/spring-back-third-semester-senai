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
    private String name;
    @NotBlank(message = "O campo não pode estar vazio.")
    @Email
    private String email;
    private List<Loan> loans = new ArrayList<>();

    public UserRequestDTO(String name, String email, List<Loan> loans) {
        this.name = name;
        this.email = email;
        this.loans = loans;
    }
}
