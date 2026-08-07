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
    public String savePedido(@Valid PedidoRequestDTO pedidoRequestDTO, String authenticatedEmail, boolean admin) {

        Optional<Usuario> usuarioPedido = admin
                ? usuarioRepository.findById(pedidoRequestDTO.getCliente_id())
                : Optional.ofNullable(usuarioRepository.findByEmail(authenticatedEmail));

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
    public List<PedidoResponseDTO> mostrar(String authenticatedEmail, boolean admin) {
        List<Pedido> pedidos = admin ? pedidoRepository.findAll() : pedidoRepository.findByClienteEmail(authenticatedEmail);
        List<PedidoResponseDTO> listaDePedidos = pedidos.stream().map(PedidoResponseDTO::new).toList();
        return listaDePedidos;
    }


    //procurar pedido por id
    public PedidoResponseDTO searchPedido(UUID id, String authenticatedEmail, boolean admin) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isPresent() && (admin || pedido.get().getCliente().getEmail().equals(authenticatedEmail))) {
            return new PedidoResponseDTO(pedido.get());
        }
        return null;
    }


    //deletar pedido
    public boolean deletePedido(UUID id, String authenticatedEmail, boolean admin) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isPresent() && (admin || pedido.get().getCliente().getEmail().equals(authenticatedEmail))) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
