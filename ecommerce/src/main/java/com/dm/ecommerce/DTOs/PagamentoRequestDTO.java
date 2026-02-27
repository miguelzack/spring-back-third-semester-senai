package com.dm.ecommerce.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

public class PagamentoRequestDTO {
    private UUID pedido_id;
    private LocalDate momento;

    public PagamentoRequestDTO(UUID pedido_id, LocalDate momento) {
        this.pedido_id = pedido_id;
        this.momento = momento;
    }
}
