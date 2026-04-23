package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.entity.ItemDoPedido;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class ItemDoPedidoResponseDTO {

    private UUID pedido_id;
    private UUID produto_id;
    private Integer quantidade;
    private Double preco;

    public ItemDoPedidoResponseDTO(ItemDoPedido item) {
        this.pedido_id = item.getPedido().getId();
        this.produto_id = item.getProduto().getId();
        this.quantidade = item.getQuantidade();
        this.preco = item.getPreco();
    }

    @Override
    public String toString() {
        return "ItemDoPedido{" +
                "pedido_id=" + pedido_id +
                ", produto_id=" + produto_id +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}