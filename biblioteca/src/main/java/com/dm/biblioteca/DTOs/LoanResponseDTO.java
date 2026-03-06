package com.dm.biblioteca.DTOs;

import com.dm.biblioteca.entity.Loan;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class LoanResponseDTO {
    private long id;
    private long user_id;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public LoanResponseDTO(Loan loan) {
        this.id = loan.getId();
        this.user_id = loan.getUser().getId();
        this.dataEmprestimo = loan.getDataEmprestimo();
        this.dataDevolucao = loan.getDataDevolucao();
    }

    @Override
    public String toString() {
        return "LoanResponseDTO{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
