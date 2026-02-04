package com.List.ToDo.dto;

import java.time.LocalDate;

import com.List.ToDo.entity.Status;
import com.List.ToDo.entity.Tarefa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TarefaResponseDTO {
    private long id;
    private long usuario_id;
    private String nome;
    private String descricao;
    private Status status;
    private LocalDate dtInicio;
    private LocalDate dtFim;

    public TarefaResponseDTO(Tarefa tarefa) {
        this.id = tarefa.getId();
        this.usuario_id = tarefa.getUsuario().getId();
        this.nome = tarefa.getNome();
        this.descricao = tarefa.getDescricao();
        this.status = tarefa.getStatus();
        this.dtInicio = tarefa.getDtInicio();
        this.dtFim = tarefa.getDtFim();
    }

    @Override
    public String toString() {
        return "Tarefa localizada! " +
                "ID da tarefa: " + id + "; ID do usuário que criou a tarefa: " + usuario_id +
                "; Nome da tarefa: " + nome + "; Descrição da tarefa: " + descricao + "; Status da tarefa: " + status + "; Data de início da tarefa: " + dtInicio +
                "; Data de fim da tarefa: " + dtFim + ".";
    }
}
