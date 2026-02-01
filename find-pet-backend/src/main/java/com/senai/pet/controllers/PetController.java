package com.senai.pet.controllers;

import com.senai.pet.dtos.PetDto;
import com.senai.pet.services.PetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService){
        this.petService = petService;
    }
    @PostMapping("/savePet")
    public ResponseEntity<?> savePet(@Valid @RequestBody PetDto dto){
        petService.salvar(dto);
        return ResponseEntity.ok("SALVO");
    }
}
