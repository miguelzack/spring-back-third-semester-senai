package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.entity.Pedido;
import com.dm.ecommerce.entity.enums.StatusDoPedido;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoResponseDTO {

    private UUID id;
    private UUID cliente_id;
    private LocalDate momento;
    private StatusDoPedido status;
    private double subtotal;
    private List<ItemDoPedidoResponseDTO> items;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.cliente_id = pedido.getCliente().getId();
        this.status = pedido.getStatus();
        this.momento = pedido.getMomento();
        this.subtotal = pedido.getSubtotal();

        this.items = pedido.getItems()
                .stream()
                .map(ItemDoPedidoResponseDTO::new)
                .toList();
    }

    @Override
    public String toString() {
        return "Pedido localizado: " +
                "cliente_id=" + cliente_id +
                ", id=" + id +
                ", momento=" + momento +
                ", status=" + status +
                ", subtotal=" + subtotal +
                ", items=" + items + ".";
    }
}