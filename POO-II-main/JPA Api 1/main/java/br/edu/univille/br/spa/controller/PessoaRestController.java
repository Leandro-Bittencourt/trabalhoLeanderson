package br.edu.univille.br.spa.controller;

import br.edu.univille.br.spa.modelo.entidade.Pessoa;
import br.edu.univille.br.spa.servico.PessoaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// API REST - GET PUT PATCH DELETE POST
@RestController
@RequestMapping("api/pessoa")
public class PessoaRestController {

    @Autowired
    private PessoaServico servico;

    @GetMapping
    public List<Pessoa> obterTodos(){
        return servico.obterTodos();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Pessoa> obterPeloId(@PathVariable Long id){
        var opt = servico.obterPeloId(id);
        return opt.map(pessoa -> new ResponseEntity<>(pessoa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
