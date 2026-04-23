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
    private LocalDate dataLoan;
    private LocalDate dataReturn;

    public LoanResponseDTO(Loan loan) {
        this.id = loan.getId();
        this.user_id = loan.getUser().getId();
        this.dataLoan = loan.getDataLoan();
        this.dataReturn = loan.getDataReturn();
    }

    @Override
    public String toString() {
        return "LoanResponseDTO{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", dataLoan=" + dataLoan +
                ", dataReturn=" + dataReturn +
                '}';
    }
}
