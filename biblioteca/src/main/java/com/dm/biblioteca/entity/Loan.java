package com.dm.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private User user;
    private LocalDate dataLoan;
    private LocalDate dataReturn;

    public Loan(User user) {
        this.user = user;
        this.dataLoan = LocalDate.now();
        this.dataReturn = dataLoan.plusDays(10);
    }
}
