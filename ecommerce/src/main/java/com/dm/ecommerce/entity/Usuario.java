package com.dm.ecommerce.entity;

import com.dm.ecommerce.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    //Não pode criar usuários com mesmo nomes????
    //O retorno ta meio de febem
    //Fazer um seachbyuser
    //Fazer um put no usuario
    //Ver pra tentar melhorar o tratamento de dados de excessão (tipo campo vazio e etc)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Role roles;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pedido> pedidos = new ArrayList<>();

    public Usuario(@NotBlank(message = "O campo não pode estar vazio.") String nome, @NotBlank(message = "O campo não pode estar vazio.") @Email(message = "Digite um email válido") String email, @NotBlank(message = "O campo não pode estar vazio.") String telefone, @NotBlank(message = "O campo não pode estar vazio.") @Size(min = 6, max = 20, message = "A senha deve ter entre 6 a 20 caracteres.") String senha, @NotBlank(message = "O campo não pode estar vazio.") Role roles) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.roles = roles;
    }
}
