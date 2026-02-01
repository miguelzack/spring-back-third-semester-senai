package com.senai.pet.dtos;

import com.senai.pet.entities.PetEntity;
import com.senai.pet.enums.Porte;
import com.senai.pet.enums.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PetDto {
    @NotBlank
    private String nome;
    private int idade;
    private String raca;
    @NotNull
    private Porte porte;
    @NotNull
    private Tipo tipo;

    public PetDto(PetEntity pet) {
        this.nome = pet.getNome();
        this.idade = pet.getIdade();
        this.raca = pet.getRaca();
        this.porte = pet.getPorte();
        this.tipo = pet.getTipo();
    }
}
