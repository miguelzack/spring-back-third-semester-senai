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

import com.List.ToDo.dto.TarefaRequestDTO;
import com.List.ToDo.dto.TarefaResponseDTO;
import com.List.ToDo.entity.Tarefa;
import com.List.ToDo.service.TarefaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "tarefa")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping(value = "cadastro")
    public ResponseEntity<?> saveTask(@Valid @RequestBody TarefaRequestDTO task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.saveTask(task));
    }

    @GetMapping(value = "view")
    public List<TarefaResponseDTO> mostrar() {
        return tarefaService.mostrar();
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> searchById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.searchById(id));
    }



    @PutMapping("update/{id}")
    public ResponseEntity<?> atualizar(@Valid @PathVariable int id, @RequestBody Tarefa novaTarefa) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.atualizar(id, novaTarefa));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.deleteTarefa(id));
    }
}
