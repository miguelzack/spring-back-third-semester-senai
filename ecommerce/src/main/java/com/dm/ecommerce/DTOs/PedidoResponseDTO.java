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
    private LocalDate momento;
    private StatusDoPedido status;

    public PedidoResponseDTO(Pedido pedido) {
        this.cliente_id = pedido.getCliente().getId();
        this.status = pedido.getStatus();
        this.momento = pedido.getMomento();
    }
}
