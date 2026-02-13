package com.List.ToDo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.List.ToDo.dto.UsuarioRequestDTO;
import com.List.ToDo.dto.UsuarioResponseDTO;
import com.List.ToDo.entity.Usuario;
import com.List.ToDo.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "cadastro")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioRequestDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUser(user));
    }


    //fala que não acha o usuario
    @PostMapping(value = "login")
    public ResponseEntity<?> login(@Valid @RequestBody UsuarioRequestDTO user) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioService.login(user));
    }

    @GetMapping(value = "view")
    public List<UsuarioResponseDTO> mostrar() {
        return usuarioService.mostrar();
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> searchById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.searchById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(@Valid @PathVariable int id, @RequestBody Usuario novoUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizar(id, novoUsuario));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.deleteUsuario(id));
    }

    @GetMapping(value = "viewtasks/{id}")
    public  ResponseEntity<?> searchByUser(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.searchByUser(id));
    }
}
