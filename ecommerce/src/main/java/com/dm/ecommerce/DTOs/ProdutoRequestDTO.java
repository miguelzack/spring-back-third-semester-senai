package com.dm.ecommerce.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

public class ProdutoRequestDTO {
    @NotBlank(message = "Esse campo não pode ser vazio.")
    private String nome;
    @NotBlank(message = "Esse campo não pode ser vazio.")
    private String descricao;
    private Double preco;
    private MultipartFile imgUrl;
    private Set<UUID> categoriaIds;

    public ProdutoRequestDTO(String nome, String descricao, Double preco, MultipartFile imgUrl) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgUrl = imgUrl;
    }
}
