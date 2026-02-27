package com.List.ToDo.dto;

import java.time.LocalDate;

import com.List.ToDo.entity.Status;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class TarefaRequestDTO {

    private long usuario_id;
    @NotBlank(message = "O nome não pode ser vazio e/ou nulo.")
    private String nome;
    @NotBlank(message = "A descrição não pode ser vazia e/ou nula.")
    private String descricao;
    private Status status;
    private LocalDate dtInicio;
    private LocalDate dtFim;

    public TarefaRequestDTO(long usuario_id, String nome, String descricao, Status status, LocalDate dtInicio, LocalDate dtFim) {
        this.usuario_id = usuario_id;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.dtInicio = LocalDate.now();
        this.dtFim = dtFim;
    }
}
