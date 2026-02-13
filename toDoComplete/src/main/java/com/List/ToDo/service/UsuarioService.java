package com.List.ToDo.service;

import java.util.List;
import java.util.Optional;

import com.List.ToDo.dto.TarefaResponseDTO;
import com.List.ToDo.entity.Tarefa;
import com.List.ToDo.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import com.List.ToDo.dto.UsuarioRequestDTO;
import com.List.ToDo.dto.UsuarioResponseDTO;
import com.List.ToDo.entity.Usuario;
import com.List.ToDo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, TarefaRepository tarefaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tarefaRepository = tarefaRepository;
    }

    // criar usuario
    public String saveUser(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario(usuarioRequestDTO.getNome(), usuarioRequestDTO.getEmail(),
                usuarioRequestDTO.getSenha());

        usuarioRepository.save(usuario);
        return "Usuário criado com sucesso.";

    }

    // login
    public String login(UsuarioRequestDTO user) {
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());


        if (findUser == null) {

            return "Usuário não encontrado.";
        } else {
            if (findUser.getSenha().equals(user.getSenha())) {
                return "Logado com sucesso.";
            } else {

                return "Senha incorreta.";
            }
        }
    }

    // procura por id
    public String searchById(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            UsuarioResponseDTO dto = new UsuarioResponseDTO(usuario.get());
            return dto.toString();
        } else {
            return "Esse ID não é válido. Tente novamente.";
        }
    }

    // mostrar todos os usuarios
    public List<UsuarioResponseDTO> mostrar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> listadeUsuarios = usuarios.stream().map(UsuarioResponseDTO::new).toList();

        return listadeUsuarios;
    }

    // atualizar nome do usuario
    public String atualizar(int id, Usuario novoUsuario) {
        Optional<Usuario> UsuarioExistente = usuarioRepository.findById(id);

        if (UsuarioExistente.isPresent()) {
            Usuario Usuario = UsuarioExistente.get();
            Usuario.setNome(novoUsuario.getNome());
//			Usuario.setSenha(novoUsuario.getSenha());
            usuarioRepository.save(Usuario);
            return "O nome foi modificado para " + Usuario.getNome() + ".";

        } else {
            return "Não foi achado o usuário.";
        }
    }

    // buscar tarefa por usuario
    public List<TarefaResponseDTO> searchByUser(long usuarioid) {
        Optional<Usuario> usuariosearchtask = usuarioRepository.findById(usuarioid);

        if (usuariosearchtask.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        Usuario usuario = usuariosearchtask.get();

        List<Tarefa> listaDeTarefasUser = tarefaRepository.findByUsuario(usuario);

        return listaDeTarefasUser.stream()
                .map(TarefaResponseDTO::new)
                .toList();

    }

    // deletar usuario
    public String deleteUsuario(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            usuarioRepository.deleteById(id);
            return "Usuário deletado com sucesso.";
        } else {
            return "Esse ID não existe.";
        }
    }

}
