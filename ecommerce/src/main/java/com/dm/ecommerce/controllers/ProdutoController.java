package com.dm.ecommerce.controllers;

import com.dm.ecommerce.DTOs.ProdutoRequestDTO;
import com.dm.ecommerce.DTOs.ProdutoResponseDTO;
import com.dm.ecommerce.entity.Produto;
import com.dm.ecommerce.service.PhotoService;
import com.dm.ecommerce.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "produto")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final PhotoService photoService;

    public ProdutoController(ProdutoService produtoService, PhotoService photoService) {
        this.produtoService = produtoService;
        this.photoService = photoService;
    }

    @PostMapping(value = "cadastro", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveProduto(@Valid @ModelAttribute ProdutoRequestDTO produto) throws IOException {
        String pathPhoto = photoService.savePhoto(produto.getImgUrl());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoService.saveProduto(produto, pathPhoto));
    }

    @GetMapping(value = "view")
    public List<ProdutoResponseDTO> mostrar(@RequestParam(required = false) UUID categoriaId) {
        return produtoService.mostrar(categoriaId);
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> searchById(@PathVariable UUID id) {
        ProdutoResponseDTO produto = produtoService.buscaPorId(id);
        return produto == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(produto);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@Valid @PathVariable UUID id, @RequestBody Produto novoProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizar(id, novoProduto));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable UUID id) {
        boolean deleted = produtoService.deleteProduto(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
