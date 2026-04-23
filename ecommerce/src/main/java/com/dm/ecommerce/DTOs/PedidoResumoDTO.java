package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.entity.Pedido;
import com.dm.ecommerce.entity.enums.StatusDoPedido;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PedidoResumoDTO {

    private UUID id;
    private LocalDate momento;
    private StatusDoPedido status;
    private double subtotal;

    public PedidoResumoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.momento = pedido.getMomento();
        this.status = pedido.getStatus();
        this.subtotal = pedido.getSubtotal();
    }
}