package com.dm.ecommerce.service;


import com.dm.ecommerce.DTOs.ProdutoRequestDTO;
import com.dm.ecommerce.DTOs.ProdutoResponseDTO;
import com.dm.ecommerce.entity.Produto;
import com.dm.ecommerce.repositories.ProdutoRepository;
import com.dm.ecommerce.repositories.CategoriaRepository;
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
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public String saveProduto(@Valid ProdutoRequestDTO produtoRequestDTO, String pathPhoto) {
        Produto produto = new Produto(produtoRequestDTO.getNome(), produtoRequestDTO.getDescricao(), produtoRequestDTO.getPreco(), pathPhoto    );
        if (produtoRequestDTO.getCategoriaIds() != null && !produtoRequestDTO.getCategoriaIds().isEmpty()) {
            produto.setCategorias(new java.util.HashSet<>(categoriaRepository.findAllById(produtoRequestDTO.getCategoriaIds())));
        }
        produtoRepository.save(produto);
        return "Produto criado com sucesso.";
    }

    public ProdutoResponseDTO buscaPorId(UUID id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            return new ProdutoResponseDTO(produto.get());
        }
        return null;
    }

    public List<ProdutoResponseDTO> mostrar() {
        return mostrar(null);
    }

    public List<ProdutoResponseDTO> mostrar(UUID categoriaId) {
        List<Produto> produtos = categoriaId == null
                ? produtoRepository.findAll()
                : produtoRepository.findDistinctByCategorias_Id(categoriaId);
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

    public boolean deleteProduto(UUID id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
