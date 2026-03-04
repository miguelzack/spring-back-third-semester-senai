package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.entity.Pedido;
import com.dm.ecommerce.enums.StatusDoPedido;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter

public class PedidoResponseDTO {
    private UUID cliente_id;
    private UUID id;
    private LocalDate momento;
    private StatusDoPedido status;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.cliente_id = pedido.getCliente().getId();
        this.status = pedido.getStatus();
        this.momento = pedido.getMomento();
    }

    @Override
    public String toString() {
        return "Pedido localizado: " +
                "cliente_id=" + cliente_id +
                ", id=" + id +
                ", momento=" + momento +
                ", status=" + status + ".";
    }
}

