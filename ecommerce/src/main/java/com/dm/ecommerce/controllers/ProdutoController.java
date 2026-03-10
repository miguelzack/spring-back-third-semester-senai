package com.dm.ecommerce.controllers;

import com.dm.ecommerce.DTOs.ProdutoRequestDTO;
import com.dm.ecommerce.DTOs.ProdutoResponseDTO;
import com.dm.ecommerce.entity.Produto;
import com.dm.ecommerce.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping(value = "cadastro")
    public ResponseEntity<?> saveProduto(@Valid @RequestBody ProdutoRequestDTO produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.saveProduto(produto));
    }

    @GetMapping(value = "view")
    public List<ProdutoResponseDTO> mostrar() {
        return produtoService.mostrar();
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> searchById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscaPorId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@Valid @PathVariable UUID id, @RequestBody Produto novoProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizar(id, novoProduto));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.deleteProduto(id));
    }
}
