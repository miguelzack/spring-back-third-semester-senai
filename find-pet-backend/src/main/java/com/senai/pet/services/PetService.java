package com.senai.pet.services;

import com.senai.pet.dtos.PetDto;
import com.senai.pet.entities.PetEntity;
import com.senai.pet.repositories.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public String salvar(PetDto dto) {
        PetEntity pet = new PetEntity(dto.getNome(), dto.getIdade(), dto.getRaca(), dto.getPorte(), dto.getTipo());
        petRepository.save(pet);
        return "salvo com sucesso!";
    }
}
