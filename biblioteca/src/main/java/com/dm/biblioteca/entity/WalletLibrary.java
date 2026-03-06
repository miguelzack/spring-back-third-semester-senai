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
public class WalletLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroCarteira;
    @OneToOne
    @MapsId
    @JsonBackReference
    private User user;
    private LocalDate dataEmissao;
    private boolean isValid;


    public WalletLibrary(User user) {
        this.user = user;
        this.dataEmissao = LocalDate.now();
        this.isValid = true;
    }
}
