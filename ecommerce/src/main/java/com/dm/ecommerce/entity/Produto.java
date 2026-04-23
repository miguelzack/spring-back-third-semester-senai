package com.dm.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String descricao;
    private Double preco;
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "tb_produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias = new HashSet<>();

    @OneToMany(mappedBy = "id.produto")
    @JsonIgnore
    private Set<ItemDoPedido> items = new HashSet<>();

    public List<Pedido> getPedido() {
        return items.stream().map(ItemDoPedido::getPedido).toList();
    }

    public Produto(@NotBlank(message = "Esse campo não pode ser vazio.") String nome, @NotBlank(message = "Esse campo não pode ser vazio.") String descricao, Double preco, @NotBlank(message = "Esse campo não pode ser vazio.") String imgUrl) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgUrl = imgUrl;
    }

}
