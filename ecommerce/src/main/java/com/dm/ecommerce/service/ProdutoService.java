package com.dm.ecommerce.service;


import com.dm.ecommerce.DTOs.ProdutoRequestDTO;
import com.dm.ecommerce.DTOs.ProdutoResponseDTO;
import com.dm.ecommerce.entity.Produto;
import com.dm.ecommerce.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public String saveProduto(@Valid ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto(produtoRequestDTO.getNome(), produtoRequestDTO.getDescricao(), produtoRequestDTO.getPreco(), produtoRequestDTO.getImgUrl());
        produtoRepository.save(produto);
        return "Produto criado com sucesso.";
    }

    public String buscaPorId(UUID id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            ProdutoResponseDTO dto = new ProdutoResponseDTO(produto.get());
            return dto.toString();
        } else {
            return "Esse ID não é válido.";
        }
    }

    public List<ProdutoResponseDTO> mostrar() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> listaDeProdutos = produtos.stream().map(ProdutoResponseDTO::new).toList();
        return listaDeProdutos;
    }

    public String atualizar(UUID id, Produto novoProduto) {
        Optional<Produto> ProdutoExistente = produtoRepository.findById(id);

        if (ProdutoExistente.isPresent()) {
            Produto Produto = ProdutoExistente.get();
            Produto.setPreco(novoProduto.getPreco());
            produtoRepository.save(Produto);
            return "O preço foi modificado para " + Produto.getPreco() + ".";

        } else {
            return "Não foi achado o usuário.";
        }
    }

    public String deleteProduto(UUID id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produtoRepository.deleteById(id);
            return "Produto deletado com sucesso.";
        } else {
            return "ID inválido.";
        }
    }
}
