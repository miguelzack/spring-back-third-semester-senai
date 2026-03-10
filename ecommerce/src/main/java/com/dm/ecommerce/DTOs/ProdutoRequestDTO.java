package com.dm.ecommerce.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProdutoRequestDTO {
    @NotBlank(message = "Esse campo não pode ser vazio.")
    private String nome;
    @NotBlank(message = "Esse campo não pode ser vazio.")
    private String descricao;
    private Double preco;
    @NotBlank(message = "Esse campo não pode ser vazio.")
    private String imgUrl;

    public ProdutoRequestDTO(String nome, String descricao, Double preco, String imgUrl) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgUrl = imgUrl;
    }
}
