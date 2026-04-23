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

    public LoanRequestDTO(long user_id) {
        this.user_id = user_id;
    }
}
