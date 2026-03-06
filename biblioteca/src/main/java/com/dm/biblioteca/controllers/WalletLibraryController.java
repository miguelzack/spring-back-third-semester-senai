package com.dm.biblioteca.controllers;

import com.dm.biblioteca.DTOs.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "carteira")
public class WalletLibraryController {
    private final com.dm.biblioteca.services.WalletLibraryService walletLibraryService;

    public WalletLibraryController(com.dm.biblioteca.services.WalletLibraryService walletLibraryService) {
        this.walletLibraryService = walletLibraryService;
    }


    @PostMapping(value = "cadastro")
    public ResponseEntity<?> saveWallet(@Valid @RequestBody WalletLibraryRequestDTO wallet) {
        return ResponseEntity.status(HttpStatus.CREATED).body(walletLibraryService.saveWallet(wallet));
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> viewById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(walletLibraryService.searchById(id));
    }

}
