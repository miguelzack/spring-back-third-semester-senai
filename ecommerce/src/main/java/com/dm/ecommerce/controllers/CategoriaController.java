package com.dm.ecommerce.controllers;

import com.dm.ecommerce.DTOs.CategoriaResponseDTO;
import com.dm.ecommerce.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("view")
    public List<CategoriaResponseDTO> listar() {
        return categoriaService.listar();
    }

    @GetMapping("view/{id}")
    public ResponseEntity<?> buscar(@PathVariable UUID id) {
        CategoriaResponseDTO categoria = categoriaService.buscar(id);
        return categoria == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(categoria);
    }
}
