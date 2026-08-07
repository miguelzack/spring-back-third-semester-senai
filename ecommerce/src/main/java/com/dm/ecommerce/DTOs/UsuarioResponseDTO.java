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
    private Role roles;
//    private String imgUrl;

    private List<PedidoResumoDTO> pedidos = new ArrayList<>();

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.roles = usuario.getRoles();
//        this.imgUrl = usuario.getImgUrl();

        this.pedidos = usuario.getPedidos().stream().map(PedidoResumoDTO::new).toList();
    }

}
