package com.dm.ecommerce.entity;

import com.dm.ecommerce.enums.StatusDoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate momento;
    private StatusDoPedido status;

    @ManyToOne
    @JoinColumn
    private Usuario cliente;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pagamento pagamento;


    public Pedido(Usuario cliente, LocalDate momento, StatusDoPedido status) {
        this.cliente = cliente;
        this.momento = LocalDate.now();
        this.status = status;
    }
}


