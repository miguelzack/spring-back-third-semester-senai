package com.dm.ecommerce.controllers;


import com.dm.ecommerce.DTOs.LoginRequestDTO;
import com.dm.ecommerce.DTOs.UsuarioRequestDTO;
import com.dm.ecommerce.DTOs.UsuarioResponseDTO;
import com.dm.ecommerce.service.PhotoService;
import com.dm.ecommerce.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final PhotoService photoService;
    
    public UsuarioController(UsuarioService usuarioService, PhotoService photoService) {
        this.usuarioService = usuarioService;
        this.photoService = photoService;
    }

    @PostMapping(value = "cadastro", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveUser(@Valid @ModelAttribute UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        String pathPhoto = photoService.savePhoto(usuarioRequestDTO.getImgUrl());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuarioRequestDTO, pathPhoto));
    }

//    @PostMapping(value = "login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioService.login(loginRequestDTO));
//    }

    @GetMapping(value = "view")
    public List<UsuarioResponseDTO> mostrar() {
        return usuarioService.mostrar();
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.deleteUsuario(id));
    }
}
