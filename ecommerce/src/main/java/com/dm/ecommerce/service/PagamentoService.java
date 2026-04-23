package com.dm.ecommerce.service;

import com.dm.ecommerce.DTOs.PagamentoRequestDTO;
import com.dm.ecommerce.DTOs.PagamentoResponseDTO;
import com.dm.ecommerce.entity.Pagamento;
import com.dm.ecommerce.entity.Pedido;
import com.dm.ecommerce.entity.enums.StatusDoPedido;
import com.dm.ecommerce.repositories.PagamentoRepository;
import com.dm.ecommerce.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public String savePagamento(PagamentoRequestDTO pagamentoRequestDTO) {
        Optional<Pedido> pedidoPagamento = pedidoRepository.findById(pagamentoRequestDTO.getPedido_id());
        if (pedidoPagamento.isEmpty()) {
            return "Pedido não encontrado. Digite ID de um pedido válido.";
        }

        Pedido pedidoid = pedidoPagamento.get();
        pedidoid.setStatus(StatusDoPedido.PAGO);

        Pagamento pagamento = new Pagamento(pedidoid, pagamentoRequestDTO.getMomento());
        pagamentoRepository.save(pagamento);

        return "O pagamento foi criado com sucesso.";

    }

    public List<PagamentoResponseDTO> mostrar() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        List<PagamentoResponseDTO> listaDePagamentos = pagamentos.stream().map(PagamentoResponseDTO::new).toList();
        return  listaDePagamentos;
    }



}
