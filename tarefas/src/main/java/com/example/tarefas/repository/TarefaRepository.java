package com.example.tarefas.repository;

import com.example.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByFinalizadoFalse();
    List<Tarefa> findByFinalizadoTrue();
    List<Tarefa> findByDataPrevistaFinalizacaoBeforeAndFinalizadoFalse(LocalDate dataPrevistaFinalizacao);
    List<Tarefa> findByDataPrevistaFinalizacaoBetweenAndFinalizadoFalse(LocalDate inicio, LocalDate fim);
}
