package com.dm.ecommerce.entity;

import com.dm.ecommerce.entity.enums.StatusDoPedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @OneToMany(mappedBy = "id.pedido", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private Set<ItemDoPedido> items = new HashSet<>();

    public List<Produto> getProduto() {
        return items.stream().map(ItemDoPedido::getProduto).toList();
    }

    public double getSubtotal() {
        return items.stream()
                .mapToDouble(item -> item.getPreco() * item.getQuantidade())
                .sum();
    }


    public Pedido(Usuario cliente, LocalDate momento, StatusDoPedido status, Pagamento pagamento, Set<ItemDoPedido> items) {
        this.cliente = cliente;
        this.momento = momento;
        this.status = status;
        this.pagamento = pagamento;
        this.items = items;
    }

    public Pedido(Usuario cliente, LocalDate momento, StatusDoPedido status) {
        this.cliente = cliente;
        this.momento = LocalDate.now();
        this.status = StatusDoPedido.AGUARDANDO_PAGAMENTO;
    }
}


