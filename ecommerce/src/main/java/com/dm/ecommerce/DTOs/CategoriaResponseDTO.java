package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.entity.Categoria;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CategoriaResponseDTO {
    private final UUID id;
    private final String nome;

    public CategoriaResponseDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
