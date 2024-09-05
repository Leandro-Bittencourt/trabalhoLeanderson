package com.example.tarefas.service;

import com.example.tarefas.model.Tarefa;
import com.example.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public Tarefa criarTarefa(Tarefa tarefa) {
        validarTarefaParaCriacao(tarefa);
        return tarefaRepository.save(tarefa);
    }

    @Override
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    @Override
    public Optional<Tarefa> buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    @Override
    public Tarefa atualizarTarefa(Long id, Tarefa tarefa) {
        if (!tarefaRepository.existsById(id)) {
            throw new IllegalArgumentException("Tarefa com ID " + id + " não encontrada.");
        }

        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);

        if (tarefaExistente.isPresent()) {
            Tarefa tarefaAtual = tarefaExistente.get();

            if (tarefaAtual.isFinalizado()) {
                throw new IllegalStateException("Tarefa finalizada não pode ser modificada.");
            }

            validarTarefaParaAtualizacao(tarefa);
            tarefa.setId(id);
            return tarefaRepository.save(tarefa);
        }

        return null;
    }

    @Override
    public boolean deletarTarefa(Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            if (tarefa.get().isFinalizado()) {
                throw new IllegalStateException("Tarefa finalizada não pode ser excluída.");
            }
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Tarefa> listarTarefasNaoFinalizadas() {
        return tarefaRepository.findByFinalizadoFalse();
    }

    @Override
    public List<Tarefa> listarTarefasFinalizadas() {
        return tarefaRepository.findByFinalizadoTrue();
    }

    @Override
    public List<Tarefa> listarTarefasAtrasadas() {
        return tarefaRepository.findByDataPrevistaFinalizacaoBeforeAndFinalizadoFalse(LocalDate.now());
    }

    @Override
    public List<Tarefa> listarTarefasNaoFinalizadasEntreDatas(LocalDate inicio, LocalDate fim) {
        if (inicio.isAfter(fim)) {
            throw new IllegalArgumentException("Data de início não pode ser após a data de fim.");
        }
        return tarefaRepository.findByDataPrevistaFinalizacaoBetweenAndFinalizadoFalse(inicio, fim);
    }

    private void validarTarefaParaCriacao(Tarefa tarefa) {
        if (tarefa.getTitulo() == null || tarefa.getTitulo().isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório.");
        }
        if (tarefa.getTitulo().length() < 5) {
            throw new IllegalArgumentException("Título deve ter pelo menos 5 caracteres.");
        }
        if (tarefa.getDataPrevistaFinalizacao() == null) {
            throw new IllegalArgumentException("Data prevista de finalização é obrigatória.");
        }
    }

    private void validarTarefaParaAtualizacao(Tarefa tarefa) {
        if (tarefa.getTitulo() == null || tarefa.getTitulo().isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório.");
        }
        if (tarefa.getTitulo().length() < 5) {
            throw new IllegalArgumentException("Título deve ter pelo menos 5 caracteres.");
        }
    }
}
