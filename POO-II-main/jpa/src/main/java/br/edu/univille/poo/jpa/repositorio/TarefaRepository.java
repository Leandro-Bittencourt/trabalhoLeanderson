package br.edu.univille.poo.jpa.repositorio;

import br.edu.univille.poo.jpa.entidade.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAllByTitleContaining(String titulo);

    List<Tarefa> findAllByStatus(boolean finalizado);

    List<Tarefa> findAllByDeadline(Time previsao_finalizar);
}
