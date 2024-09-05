package br.edu.univille.br.spa.servico;

import br.edu.univille.br.spa.modelo.entidade.Pessoa;
import br.edu.univille.br.spa.modelo.repositorio.PessoaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServico {

    // Injeção de Dependência
    @Autowired
    private PessoaRepositorio repositorio;

    public List<Pessoa> obterTodos(){
        return repositorio.findAll();
    }

    public Optional<Pessoa> obterPeloId(long id){
        return repositorio.findById(id);
    }
}
