package com.dm.ecommerce.repositories;

import com.dm.ecommerce.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findById(UUID clienteId);
    Usuario findByEmail(String email);
    void deleteById(UUID id);
}
