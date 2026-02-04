package com.List.ToDo.dto;

import com.List.ToDo.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UsuarioResponseDTO {
    private long id;
    private String nome;
    private String email;

    public UsuarioResponseDTO(Usuario usuario) {
        super();
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }


    @Override
    public String toString() {
        return "Usuário localizado! " + "Nome: " + nome + ", Email: " + email;
    }

}
