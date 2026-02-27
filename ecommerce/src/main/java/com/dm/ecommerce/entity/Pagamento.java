package com.dm.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate momento;

    @OneToOne
    @MapsId
    @JsonBackReference
    private Pedido pedido;

    public Pagamento(Pedido pedido, LocalDate momento) {
        this.pedido = pedido;
        this.momento = LocalDate.now();
    }

}
