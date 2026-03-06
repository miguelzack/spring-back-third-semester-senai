package com.dm.biblioteca.repositories;

import com.dm.biblioteca.entity.WalletLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletLibraryRepository extends JpaRepository<WalletLibrary, Integer> {
    Optional<WalletLibrary> findByNumeroCarteira(long NumeroCarteira);
}
