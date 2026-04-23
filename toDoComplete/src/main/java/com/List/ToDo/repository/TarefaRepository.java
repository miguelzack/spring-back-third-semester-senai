package com.List.ToDo.repository;

import com.List.ToDo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.List.ToDo.entity.Tarefa;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{
	
	Tarefa findByNome(String nome);
	List<Tarefa> findByUsuario(Usuario usuario);

}
