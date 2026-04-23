package com.dm.ecommerce.controllers;

import com.dm.ecommerce.DTOs.PagamentoRequestDTO;
import com.dm.ecommerce.DTOs.PagamentoResponseDTO;
import com.dm.ecommerce.DTOs.PedidoRequestDTO;
import com.dm.ecommerce.DTOs.PedidoResponseDTO;
import com.dm.ecommerce.service.PagamentoService;
import com.dm.ecommerce.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "pedido")
public class PedidoController {

    //ta faltando um search pedido by user

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping(value = "cadastro")
    public ResponseEntity<?> savePedido(@Valid @RequestBody PedidoRequestDTO pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.savePedido(pedido));
    }

    @GetMapping(value = "view")
    public List<PedidoResponseDTO> mostrar() {
        return pedidoService.mostrar();
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> searchById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.searchPedido(id));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.deletePedido(id));
    }
}
