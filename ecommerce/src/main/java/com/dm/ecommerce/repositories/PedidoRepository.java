package com.dm.ecommerce.repositories;

import com.dm.ecommerce.entity.Pedido;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByClienteEmail(String email);
    Optional<Pedido> findById(@NotBlank(message = "O ID do pedido não pode ser vazio.") UUID pedidoId);

    void deleteById(UUID id);
}
