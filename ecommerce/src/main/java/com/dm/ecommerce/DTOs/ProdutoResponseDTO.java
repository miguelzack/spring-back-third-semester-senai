package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.entity.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class ProdutoResponseDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private Double preco;
    private String imgUrl;

    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.imgUrl = produto.getImgUrl();
    }

    @Override
    public String toString() {
        return "ProdutoResponseDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
