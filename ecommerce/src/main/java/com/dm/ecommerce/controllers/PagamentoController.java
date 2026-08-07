package com.dm.ecommerce.controllers;

import com.dm.ecommerce.DTOs.PagamentoRequestDTO;
import com.dm.ecommerce.DTOs.PagamentoResponseDTO;
import com.dm.ecommerce.DTOs.PedidoResponseDTO;
import com.dm.ecommerce.service.PagamentoService;
import com.dm.ecommerce.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "pagamento")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping(value = "cadastro")
    public ResponseEntity<?> savePagamento(@Valid @RequestBody PagamentoRequestDTO pagamento, org.springframework.security.core.Authentication authentication) {
        boolean admin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.savePagamento(pagamento, authentication.getName(), admin));
    }

    @GetMapping(value = "view")
    public List<PagamentoResponseDTO> mostrar() {
        return pagamentoService.mostrar();
    }

}
