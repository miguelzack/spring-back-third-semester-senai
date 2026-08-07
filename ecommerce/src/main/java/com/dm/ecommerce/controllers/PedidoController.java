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
    public ResponseEntity<?> savePedido(@Valid @RequestBody PedidoRequestDTO pedido, org.springframework.security.core.Authentication authentication) {
        boolean admin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.savePedido(pedido, authentication.getName(), admin));
    }

    @GetMapping(value = "view")
    public List<PedidoResponseDTO> mostrar(org.springframework.security.core.Authentication authentication) {
        boolean admin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return pedidoService.mostrar(authentication.getName(), admin);
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> searchById(@PathVariable UUID id, org.springframework.security.core.Authentication authentication) {
        boolean admin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        PedidoResponseDTO pedido = pedidoService.searchPedido(id, authentication.getName(), admin);
        return pedido == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(pedido);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable UUID id, org.springframework.security.core.Authentication authentication) {
        boolean admin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean deleted = pedidoService.deletePedido(id, authentication.getName(), admin);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
