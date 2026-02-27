package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.enums.StatusDoPedido;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

public class PedidoRequestDTO {
    private UUID cliente_id;
    private LocalDate momento;
    private StatusDoPedido status;

    public PedidoRequestDTO(UUID cliente_id, LocalDate momento, StatusDoPedido status) {
        this.cliente_id = cliente_id;
        this.momento = momento;
        this.status = status;
    }
}
