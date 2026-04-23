package com.List.ToDo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "tbl_tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String nome;
    private String descricao;
    private Status status;
    private LocalDate dtInicio;
    private LocalDate dtFim;

    public Tarefa(Usuario usuario, String nome, String descricao, Status status, LocalDate dtInicio, LocalDate dtFim) {
        this.usuario = usuario;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.dtInicio = LocalDate.now();
        this.dtFim = dtFim;
    }
}
