package com.dm.ecommerce.DTOs;

import com.dm.ecommerce.entity.Pedido;
import com.dm.ecommerce.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class UsuarioRequestDTO {

    @NotBlank(message = "O campo não pode estar vazio.")
    private String nome;
    @NotBlank(message = "O campo não pode estar vazio.")
    @Email(message = "Digite um email válido")
    private String email;
    @NotBlank(message = "O campo não pode estar vazio.")
    private String telefone;
    @NotBlank(message = "O campo não pode estar vazio.")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 a 20 caracteres.")
    private String senha;
    private Role roles;
    private List<Pedido> pedidos = new ArrayList<>();

    public UsuarioRequestDTO(String nome, String email, String telefone, String senha, Role roles, List<Pedido> pedidos) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.roles = Role.USER;
        this.pedidos = pedidos;
    }
}
