package com.dm.biblioteca.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class LoanRequestDTO {
    private long user_id;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public LoanRequestDTO(long user_id, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.user_id = user_id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }
}
