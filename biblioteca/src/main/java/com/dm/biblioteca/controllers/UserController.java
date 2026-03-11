package com.dm.biblioteca.controllers;

import com.dm.biblioteca.DTOs.UserRequestDTO;
import com.dm.biblioteca.DTOs.UserResponseDTO;
import com.dm.biblioteca.entity.User;
import com.dm.biblioteca.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "usuario")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "cadastro")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequestDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @GetMapping(value = "view")
    public List<UserResponseDTO> mostrar() {
        return userService.showUsers();
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> viewById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.searchById(id));
    }

    @PutMapping(value = "update/email/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable long id, @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateEmail(id, user));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

    @GetMapping(value = "view/{id}/loans")
    public ResponseEntity<?> viewUserLoans(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.showUserLoans(id));
    }
}
