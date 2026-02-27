package com.dm.ecommerce.entity;

import com.dm.ecommerce.enums.StatusDoPedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    @JsonBackReference
    private Usuario cliente;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Pagamento pagamento;


    public Pedido(Usuario cliente, LocalDate momento, StatusDoPedido status) {
        this.cliente = cliente;
        this.momento = LocalDate.now();
        this.status = status;
    }
}


