package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.entity.Usuario;
import com.dm.ecommerce.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UsuarioResponseDTO {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private Role roles;
    private List<PedidoResumoDTO> pedidos = new ArrayList<>();

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.senha = usuario.getSenha();
        this.roles = usuario.getRoles();

        this.pedidos = usuario.getPedidos()
                .stream()
                .map(PedidoResumoDTO::new)
                .toList();
    }

    @Override
    public String toString() {
        return "UsuarioResponseDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", senha='" + senha + '\'' +
                ", roles=" + roles +
                ", pedidos=" + pedidos +
                '}';
    }
}