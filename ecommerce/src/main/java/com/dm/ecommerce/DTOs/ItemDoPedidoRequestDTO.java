package com.dm.ecommerce.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

public class ItemDoPedidoRequestDTO {

    private UUID pedido_id;
    private UUID produto_id;
    private Integer quantidade;
    private Double preco;

    public ItemDoPedidoRequestDTO(UUID pedido_id, UUID produto_id, Integer quantidade, Double preco) {
        this.pedido_id = pedido_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
        this.preco = preco;
    }
}