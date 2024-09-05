package com.example.tarefas.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Lob
    private String descricao;

    private boolean finalizado = false;

    private LocalDate dataPrevistaFinalizacao;

    private LocalDate dataFinalizacao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public LocalDate getDataPrevistaFinalizacao() {
        return dataPrevistaFinalizacao;
    }

    public void setDataPrevistaFinalizacao(LocalDate dataPrevistaFinalizacao) {
        this.dataPrevistaFinalizacao = dataPrevistaFinalizacao;
    }

    public LocalDate getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDate dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }
}

