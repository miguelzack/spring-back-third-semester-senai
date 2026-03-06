package com.dm.biblioteca.controllers;

import com.dm.biblioteca.DTOs.LoanRequestDTO;
import com.dm.biblioteca.DTOs.LoanResponseDTO;
import com.dm.biblioteca.DTOs.UserRequestDTO;
import com.dm.biblioteca.DTOs.UserResponseDTO;
import com.dm.biblioteca.entity.User;
import com.dm.biblioteca.services.LoanService;
import com.dm.biblioteca.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "emprestimo")
public class LoanController {
    private final LoanService loanService;
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping(value = "cadastro")
    public ResponseEntity<?> saveLoan(@Valid @RequestBody LoanRequestDTO loan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.saveLoan(loan));
    }

    @GetMapping(value = "view")
    public List<LoanResponseDTO> mostrar() {
        return loanService.showLoans();
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> viewById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.searchById(id));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(loanService.deleteLoan(id));
    }
}
