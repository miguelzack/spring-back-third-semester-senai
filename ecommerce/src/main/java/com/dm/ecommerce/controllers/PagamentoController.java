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
    public ResponseEntity<?> savePagamento(@Valid @RequestBody PagamentoRequestDTO pagamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.savePagamento(pagamento));
    }

    @GetMapping(value = "view")
    public List<PagamentoResponseDTO> mostrar() {
        return pagamentoService.mostrar();
    }

}
