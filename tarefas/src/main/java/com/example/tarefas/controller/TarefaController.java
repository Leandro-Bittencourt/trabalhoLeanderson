package com.example.tarefas.controller;

import com.example.tarefas.model.Tarefa;
import com.example.tarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefa);
        return ResponseEntity.ok(novaTarefa);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        return tarefaService.buscarTarefaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, tarefa);
        if (tarefaAtualizada != null) {
            return ResponseEntity.ok(tarefaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nao-finalizadas")
    public ResponseEntity<List<Tarefa>> listarTarefasNaoFinalizadas() {
        List<Tarefa> tarefasNaoFinalizadas = tarefaService.listarTarefasNaoFinalizadas();
        return ResponseEntity.ok(tarefasNaoFinalizadas);
    }

    @GetMapping("/finalizadas")
    public ResponseEntity<List<Tarefa>> listarTarefasFinalizadas() {
        List<Tarefa> tarefasFinalizadas
    }
}

