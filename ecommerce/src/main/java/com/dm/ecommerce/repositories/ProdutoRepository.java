package com.dm.ecommerce.repositories;

import com.dm.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findById(UUID id);

    void deleteById(UUID id);
}
