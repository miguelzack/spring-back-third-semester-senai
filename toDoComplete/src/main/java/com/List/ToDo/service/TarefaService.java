package com.List.ToDo.service;


import java.util.List;
import java.util.Optional;

import com.List.ToDo.entity.Usuario;
import com.List.ToDo.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.List.ToDo.entity.Status;
import com.List.ToDo.dto.TarefaRequestDTO;
import com.List.ToDo.dto.TarefaResponseDTO;
import com.List.ToDo.entity.Tarefa;
import com.List.ToDo.repository.TarefaRepository;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    public TarefaService(TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository) {
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // criar tarefa
    public String saveTask(TarefaRequestDTO tarefaRequestDTO) {

        Optional<Usuario> usuariotask = usuarioRepository.findById(tarefaRequestDTO.getUsuario_id());
        if (usuariotask.isEmpty()) {
            return "Usuário não encotrado. Digite ID de um usuário válido.";
        }

        Usuario usuarioid = usuariotask.get();

        Tarefa tarefa = new Tarefa(usuarioid, tarefaRequestDTO.getNome(), tarefaRequestDTO.getDescricao(), Status.A_FAZER, tarefaRequestDTO.getDtInicio(), tarefaRequestDTO.getDtFim());
        tarefaRepository.save(tarefa);
        TarefaResponseDTO task = new TarefaResponseDTO(tarefa);
        return "A tarefa foi criada com sucesso!";

    }

    // mostrar as tarefas
    public List<TarefaResponseDTO> mostrar() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        List<TarefaResponseDTO> listaDeTarefas = tarefas.stream().map(TarefaResponseDTO::new).toList();

        return listaDeTarefas;
    }

    // atualizar status da tarefa
    public String atualizar(int id, Tarefa novaTarefa) {
        Optional<Tarefa> TarefaExistente = tarefaRepository.findById(id);

        if (TarefaExistente.isPresent()) {
            Tarefa Tarefa = TarefaExistente.get();

            Tarefa.setNome(novaTarefa.getNome());
            Tarefa.setDescricao(novaTarefa.getDescricao());
            Tarefa.setStatus(novaTarefa.getStatus());

            tarefaRepository.save(Tarefa);

            TarefaResponseDTO dto = new TarefaResponseDTO(Tarefa);

            return "A nome, descrição e status da tarefa foram modificados.";
        } else {
            return "Não foi achado a tarefa.";
        }
    }

    // deletar tarefa
    public String deleteTarefa(int id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if (tarefa.isPresent()) {
            tarefaRepository.deleteById(id);
            return "Tarefa deletada com sucesso.";
        } else {
            return "Esse ID não existe.";
        }
    }

    // procurar tarefa
    public String searchById(int id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if (tarefa.isPresent()) {
            TarefaResponseDTO dto = new TarefaResponseDTO(tarefa.get());
            return dto.toString();
        } else {
            return "Esse ID não existe.";
        }
    }


}
