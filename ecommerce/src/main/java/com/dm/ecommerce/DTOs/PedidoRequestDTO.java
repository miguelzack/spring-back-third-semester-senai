package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.entity.enums.StatusDoPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

public class PedidoRequestDTO {

    private UUID cliente_id;
    private LocalDate momento;
    private StatusDoPedido status;
    private List<ItemDoPedidoRequestDTO> items;

    public PedidoRequestDTO(UUID cliente_id, LocalDate momento, StatusDoPedido status, List<ItemDoPedidoRequestDTO> items) {
        this.cliente_id = cliente_id;
        this.momento = momento;
        this.status = status;
        this.items = items;
    }
}