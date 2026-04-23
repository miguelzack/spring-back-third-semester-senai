package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.entity.Pagamento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter

public class PagamentoResponseDTO {
    private UUID id;
    private UUID pedido_id;
    private LocalDate momento;

    public PagamentoResponseDTO(Pagamento pagamento) {
        this.pedido_id = pagamento.getPedido().getId();
        this.id = pagamento.getId();
        this.momento = pagamento.getMomento();
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "pedido_id=" + pedido_id +
                ", momento=" + momento +
                '}';
    }
}
