package com.dm.ecommerce.service;

import com.dm.ecommerce.DTOs.CategoriaResponseDTO;
import com.dm.ecommerce.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Comparator;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponseDTO> listar() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaResponseDTO::new)
                .sorted(Comparator.comparing(CategoriaResponseDTO::getNome, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public CategoriaResponseDTO buscar(UUID id) {
        return categoriaRepository.findById(id).map(CategoriaResponseDTO::new).orElse(null);
    }
}
