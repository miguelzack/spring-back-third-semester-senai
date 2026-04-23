package com.dm.ecommerce.service;

import com.dm.ecommerce.DTOs.ItemDoPedidoRequestDTO;
import com.dm.ecommerce.DTOs.PedidoRequestDTO;
import com.dm.ecommerce.DTOs.PedidoResponseDTO;
import com.dm.ecommerce.entity.ItemDoPedido;
import com.dm.ecommerce.entity.Pedido;
import com.dm.ecommerce.entity.Produto;
import com.dm.ecommerce.entity.Usuario;
import com.dm.ecommerce.repositories.PedidoRepository;
import com.dm.ecommerce.repositories.ProdutoRepository;
import com.dm.ecommerce.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }


    //criar pedido
    public String savePedido(@Valid PedidoRequestDTO pedidoRequestDTO) {

        Optional<Usuario> usuarioPedido = usuarioRepository.findById(pedidoRequestDTO.getCliente_id());

        if (usuarioPedido.isEmpty()) {
            return "Usuário não encontrado. Digite ID de um usuário válido.";
        }

        Usuario clienteid = usuarioPedido.get();

        Pedido pedido = new Pedido(clienteid, pedidoRequestDTO.getMomento(), pedidoRequestDTO.getStatus());

        pedido.setItems(
                pedidoRequestDTO.getItems().stream().map(itemDTO -> {

                    Produto produto = produtoRepository.findById(itemDTO.getProduto_id())
                            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

                    return new ItemDoPedido(
                            pedido,
                            produto,
                            itemDTO.getQuantidade(),
                            produto.getPreco()
                    );

                }).collect(java.util.stream.Collectors.toSet())
        );

        pedidoRepository.save(pedido);

        return "O pedido foi criado.";
    }

    //mostrar todas as tarefas
    public List<PedidoResponseDTO> mostrar() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoResponseDTO> listaDePedidos = pedidos.stream().map(PedidoResponseDTO::new).toList();
        return listaDePedidos;
    }


    //procurar pedido por id
    public String searchPedido(UUID id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isPresent()) {
            PedidoResponseDTO dto = new PedidoResponseDTO(pedido.get());
            return dto.toString();
        } else {
            return "Esse ID não é válido.";
        }
    }


    //deletar pedido
    public String deletePedido(UUID id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isPresent()) {
            pedidoRepository.deleteById(id);
            return "Pedido deletado com sucesso.";
        } else {
            return "Esse ID não existe.";
        }
    }
}
